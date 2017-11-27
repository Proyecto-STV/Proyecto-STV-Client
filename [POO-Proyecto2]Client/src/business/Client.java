package business;
import domain.Persona;
import domain.Voto;
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
import javax.swing.JOptionPane;
import util.IConstants;
import view.LoginView;

public class Client extends Thread implements IConstants {

    private String funcion;
    private String identification;
    
    private PersonObverser personObverser;
    private CandidateObserver candidateObverser;
    private PuestoObserver puestoObserver;    
    
    private Persona persona;
    private Voto voto;
    
    private LoginView loginView;

    public Client(String pFuncion, String pIdentification, LoginView loginView) {
        this.funcion = pFuncion;
        this.identification = pIdentification;
        personObverser = new PersonObverser();
        personObverser.addObserver(loginView);
        this.loginView = loginView;
    }
    
    public Client (String funcion, LoginView loginView){
        this.funcion = funcion;
        this.loginView = loginView;
        this.candidateObverser = new CandidateObserver();
        this.candidateObverser.addObserver(loginView);
        
        this.puestoObserver = new PuestoObserver();
        this.puestoObserver.addObserver(loginView);
    }

    public Client(String funcion, LoginView loginView, Persona persona) {
        this.loginView = loginView;        
        this.persona = persona; 
        if (persona != null)
            this.funcion = funcion;
        else
            this.funcion = "";
    }

    public Client(String funcion, Voto voto, Persona persona) {
        this.funcion = funcion;
        this.voto = voto;      
        this.persona = persona;
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
                } else if (funcion.equalsIgnoreCase(GET_CANDIDATES)){
                    getCandidates(socket);
                } else if (funcion.equalsIgnoreCase(GET_POSITION)){
                    getPosition(socket);
                } else if (funcion.equalsIgnoreCase(CLIENT_CLOSED)){
                    clientClossing(socket);
                } else if (funcion.equalsIgnoreCase(SEND_VOTE)){
                    sendVote(socket);
                }
            }                 
        } catch (IOException | ClassNotFoundException ex) {
            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede realizar la conexión con el servidor");
        } 
    }

    private void validateIdentification(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());        
        personObverser.notifyWindow(objectIn.readObject());
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
        objectOut.writeObject(1);        
        personObverser.notifyWindow(objectIn.readObject());
    }

    private void getCandidates(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
        Object inputObject = objectIn.readObject();  
        candidateObverser.notifyWindow(inputObject);
    }

    private void getPosition(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
        Object inputObject = objectIn.readObject();  
        puestoObserver.notifyWindow(inputObject);
    }

    private void clientClossing(Socket socket) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        persona.setHaIngresado(false);
        outputStream.writeObject(persona);
    }

    private void sendVote(Socket socket) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(voto);
        persona.setHaIngresado(true);
        persona.setHaVotado(true);
        outputStream.writeObject(persona);
    }
}

