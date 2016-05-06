
package aholg.Startup;

import aholg.Controller.Controller;
import aholg.View.Console;

/**
 *  Initiates the program and passes objects required to run the program.
 * @author Anton
 */
public class Startup {
    public static void main(String[] args){
        Controller controller=new Controller();
        Console console=new Console(controller);
    }
}
