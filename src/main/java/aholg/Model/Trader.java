
package aholg.Model;

/**
 * Parses input and perform the correct command, place a buy/sell order or print
 * orders.
 *
 * @author Anton
 */
public class Trader {

    private OrderBook ordBook;

    /**
     * Order book to add orders to.
     *
     * @param ordBook Market to interact with.
     */
    public Trader(OrderBook ordBook) {
        this.ordBook = ordBook;
    }

    /**
     *  Parses given string and decides if its a sell, buy or print command.
     * @param input String to parse.
     */
    public void parseInput(String input) {
        String type = "";
        int volume = 0;
        int price = 0;
        Order order = null;

        if (input.matches("SELL\\s[1-9]\\d*@[1-9]\\d*$")) {
            String[] split = input.split("\\W+");
            volume = Integer.parseInt(split[1]);
            price = Integer.parseInt(split[2]);
            type = split[0];
            order = new Order(volume, price, type);
            ordBook.sell(order);
        } else if (input.matches("BUY\\s[1-9]\\d*@[1-9]\\d*$")) {
            String[] split = input.split("\\W+");
            volume = Integer.parseInt(split[1]);
            price = Integer.parseInt(split[2]);
            type = split[0];
            order = new Order(volume, price, type);
            ordBook.buy(order);
        } else if (input.matches("PRINT")) {
            ordBook.printOrders();
        } else {

        }
    }
}
