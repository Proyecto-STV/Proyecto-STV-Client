package business;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.IConstants;
import view.LoginView;

public class Client extends Thread implements IConstants {

    private String funcion;
    private String identification;
    private PersonObverser personObverser;
    private LoginView loginView;

    public Client(String pFuncion, String pIdentification, LoginView loginView) {
        this.funcion = pFuncion;
        this.identification = pIdentification;
        personObverser = new PersonObverser();
        this.loginView = loginView;
    }
    
    @Override
    public void run() {
        try {
            InetAddress direccionIP = InetAddress.getByName(SERVER_IP);//"127.0.0.1"
            Socket socket = new Socket(direccionIP, PORT);

            PrintStream send = new PrintStream(socket.getOutputStream());// output
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));// input

            send.println(this.funcion);//primer output

            System.out.println("Tipo de función enviada: " + this.funcion);

            System.out.println("El servidor me envía: " + receive.readLine());//primer input
            String respuestaServidor = receive.readLine();//segundo input
            System.out.println(respuestaServidor);

            if (respuestaServidor.equals(SERVER_READY)) {
                if (funcion.equalsIgnoreCase(VALIDATE_IDENTIFITATION)) {
                    send.println(identification);
                    validateIdentification(socket);
                } 
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void validateIdentification(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
        personObverser.addObserver(loginView);
        personObverser.notifyWindow(objectIn.readObject());
    }

    private void loadOriginPlayer(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
        Object inputObject = objectIn.readObject();       
    }
}

