package aholg.Controller;

import aholg.Model.Observer;
import aholg.Model.OrderBook;
import aholg.Model.Trader;

/**
 * Controller of the program. All calls to the model pass through here.
 *
 * @author Anton
 */
public class Controller {

    private OrderBook ordBook;
    private Trader trader;
    
    /**
     * Creates order book and trader to be used.
     */
    public Controller() {
        ordBook = new OrderBook();
        trader = new Trader(ordBook);
    }
    /**
     * Adds observer to the order book.
     * @param obs   Observer to be added.
     */
    public void addObserver(Observer obs) {
        ordBook.addObserver(obs);
    }
    /**
     * Call the parse method in the model to parse given input.
     * @param input    String to be parsed.
     */
    public void submit(String input) {
        trader.parseInput(input);
    }
}
