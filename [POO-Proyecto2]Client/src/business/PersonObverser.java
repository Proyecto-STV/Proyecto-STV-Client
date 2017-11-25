package business;

import java.util.Observable;

/**
 *
 * @author Nelson
 */
public class PersonObverser extends Observable{
    
    public void notifyWindow(Object value){
        this.setChanged();
        this.notifyObservers(value);
    }
}
