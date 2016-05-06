

package aholg.Model;

/**
 * Interface for an observer class. Can be used to implement different observers.
 * @author Anton
 */
public interface Observer {
    /**
     * Method to implement for different observation purposes.
     * @param information   String to notify to the listener.
     */
    void notify(String output);
}
