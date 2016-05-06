package aholg.View;

import aholg.Controller.Controller;
import aholg.Model.Observer;
import java.util.Scanner;

/**
 * The console handles input from stdin and prints output to stdout. Submits
 * input to controller.
 *
 * @author Anton
 */
public class Console implements Observer {

    public Console(Controller controller) {
        String input;
        controller.addObserver(this);

        while (true) {

            System.out.println("Enter a command: ");
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            controller.submit(input);
        }

    }

    /**
     * Prints output to stdout.
     *
     * @param output String to be printed.
     */
    @Override
    public void notify(String output) {
        System.out.println(output);
    }

}
