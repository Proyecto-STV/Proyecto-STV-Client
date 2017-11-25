package main;

import view.PrincipalWindow;

/**
 *
 * @author Nelson
 */
public class Program {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new PrincipalWindow().setVisible(true);
        });  
    }    
}
