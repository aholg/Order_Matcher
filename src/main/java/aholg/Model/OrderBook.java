package aholg.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Manages buy and sell orders. Prints a summary of all active orders.
 *
 * @author Anton
 */
public class OrderBook {

    private ArrayList<Observer> observers;
    private TreeMap<Integer, ArrayList<Order>> buyOrders;
    private TreeMap<Integer, ArrayList<Order>> sellOrders;

    public OrderBook() {
        observers = new ArrayList();
        buyOrders = new TreeMap<Integer, ArrayList<Order>>(Collections.reverseOrder());
        sellOrders = new TreeMap<Integer, ArrayList<Order>>();

    }

    /**
     * Adds an observer to be notified.
     *
     * @param obs Observer to add.
     */
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    /**
     * Goes through a given list of orders and checks if they can be matched
     * with a given order.
     *
     * @param order Order to satisfy.
     * @param ol List of orders to check.
     */
    private void match(Order order, ArrayList<Order> ol) {
        int volume = order.getVolume();
        int x = 0;
        String type = order.getType();
        TreeMap<Integer, ArrayList<Order>> mainOrders;
        TreeMap<Integer, ArrayList<Order>> sndOrders;
        if (type.equals("BUY")) {
            mainOrders = buyOrders;
            sndOrders = sellOrders;

        } else {
            mainOrders = sellOrders;
            sndOrders = buyOrders;
        }
        for (int i = 0; i < ol.size(); i++) {
            Order o = ol.get(i);
            x = volume - o.getVolume();
            if (x < 0) {
                o.setVolume(-x);
                mainOrders.remove(order.getPrice());
                printTrade(volume, o.getPrice());
                break;
            } else if (x == 0) {
                ol.remove(o);
                printTrade(volume, o.getPrice());
                mainOrders.remove(order.getPrice());
                if (ol.size() == 0) {
                    sndOrders.remove(o.getPrice());
                    break;
                }
                break;
            } else {
                order.setVolume(x);
                ol.remove(o);
                volume = x;
                i--;
                printTrade(o.getVolume(), o.getPrice());
                if (ol.size() == 0) {
                    sndOrders.remove(o.getPrice());
                    matchOrder(order);
                    break;
                }
            }
        }

    }

    /**
     * Takes a given order and checks the market for orders with correct price
     * and enough volume to meet the order. If an order goes through it is
     * removed from the market.
     *
     * @param order Order to match.
     */
    private void matchOrder(Order order) {
        int price = order.getPrice();
        if (order.getType().equals("BUY")) {
            Map.Entry<Integer, ArrayList<Order>> value = sellOrders.firstEntry();
            if (value != null && value.getKey() <= price) {
                ArrayList<Order> ol = value.getValue();
                match(order, ol);

            }
        } else if (order.getType().equals("SELL")) {
            Map.Entry<Integer, ArrayList<Order>> value = buyOrders.firstEntry();
            if (value != null && value.getKey() >= price) {
                ArrayList<Order> ol = value.getValue();
                match(order, ol);
            }
        }

    }

    /**
     * Adds a sell order to the market and checks if it can be matched with
     * current orders on the market.
     *
     * @param order Order to be added and matched.
     */
    void sell(Order order) {

        int price = order.getPrice();
        ArrayList<Order> o = sellOrders.get(price);
        if (o == null) {
            o = new ArrayList();
            o.add(order);
            sellOrders.put(price, o);
        } else {
            o.add(order);
        }

        matchOrder(order);
    }

    /**
     * Adds a sell order to the market and checks if it can be matched with
     * current orders on the market.
     *
     * @param order Order to be added and matched.
     */
    void buy(Order order) {
        int price = order.getPrice();

        ArrayList<Order> o = buyOrders.get(price);
        if (o == null) {
            o = new ArrayList();
            o.add(order);
            buyOrders.put(price, o);
        } else {
            o.add(order);
        }

        matchOrder(order);
    }

    /**
     * Prints all active orders on the market.
     */
    void printOrders() {
        StringBuilder sb = new StringBuilder();
        sb.append("---- SELL ----\n");
        Iterator<ArrayList<Order>> values = sellOrders.values().iterator();
        while (values.hasNext()) {
            ArrayList<Order> next = values.next();
            int x = 0;
            if (next.size() > 0) {
                for (int i = 0; i < next.size(); i++) {
                    x += next.get(i).getVolume();
                }
                sb.append("SELL " + x + "@" + next.get(0).getPrice());
                sb.append("\n");
            }
        }
        sb.append("---- BUY ----\n");
        values = buyOrders.values().iterator();
        while (values.hasNext()) {
            ArrayList<Order> next = values.next();
            int x = 0;
            if (next.size() > 0) {
                for (int i = 0; i < next.size(); i++) {
                    x += next.get(i).getVolume();

                }
                sb.append("BUY " + x + "@" + next.get(0).getPrice());
                sb.append("\n");
            }

        }

        notify(sb.toString());
    }

    /**
     * Prints a successful trade on the form: TYPE volume@price.
     *
     * @param amount
     * @param price
     */
    void printTrade(int amount, int price) {

        notify("TRADE " + amount + "@" + price);

    }

    /**
     * Notify all added observers(console) with a given ouput.
     *
     * @param output Output to notify observers with.
     */
    void notify(String output) {
        for (Observer obsrv : observers) {
            obsrv.notify(output);
        }
    }
}
