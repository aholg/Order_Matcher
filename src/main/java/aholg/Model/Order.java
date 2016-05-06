

package aholg.Model;

/**
 *  Contains order info.
 * @author Anton
 */
public class Order {
    private int volume;
    private int price;
    private String type;
    /**
     * 
     * @param volume     Sets the order volume.
     * @param price     Sets the order price.
     * @param type      Sets the order type.
     */
    Order(int volume,int price,String type){
        this.volume=volume;
        this.price=price;
        this.type=type;
    }
    /**
     * Returns the order price.
     * @return 
     */
    int getPrice(){
        return price;
    }
    /**
     * Returns the order volume.
     * @return 
     */
    int getVolume(){
        return volume;
    }
    /**
     * Returns the order type.
     * @return 
     */
    String getType(){
        return type;
    }
    /**
     * Modifies the order volume.
     * @param volume New volume to set.
     */
    void setVolume(int volume){
        this.volume=volume;
    }
}
