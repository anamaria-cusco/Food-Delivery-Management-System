package BusinessLayer;

import DataLayer.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IDeliveryServiceProcessing {
    //ADMIN operations
    /**
     *   @post menuItems.size() >0
     */
     void importProducts();

    /**
     *
     * @inv emptyList()
     * @pre index >= 0 && index < listSize()
     * @pre menuItems.size() >0
     * @post listSize() == listSize()@pre - 1
     */
     void deleteMenuItem (MenuItem menuItem);


    /**
     *
     * @pre menuItem!=null
     * @pre menuItem.getPrice() > 0.0f
     * @post listSize() == listSize()@pre + 1
     */
     void createMenuItem (MenuItem menuItem); //add menu item to menu


    /**
     *
     * @pre startHour >= 0 && startHour <= 23
     * @pre endHour >= 0 && endHour <= 23
     */

    // Report: time interval of the orders
    Map<Order,List<MenuItem>> generateReport0(int startHour, int endHour);


    /**
     *
     * @pre inputValue > 0
     */
    // Report: the products ordered more than a specified number of times so far.
    List<MenuItem> generateReport1(int inputValue);


    /**
     *
     * @pre inputValue >0
     * @pre amount > 0
     */
    // Report: The clients that have ordered more than a specified number of times and the value of the order was higher than a specified amount".
    List<User > generateReport2(int inputValue, int amount);


    /**
     *
     * @pre selectedDate !=null
     */
    // Report: the products ordered within a specified day with the number of times they have been ordered.
    Map<MenuItem,Long> generateReport3(Date selectedDate);

    //CLIENT operations

    /**
     *
     * @pre order != null
     * @pre menuItems.size() >= 1
     * @post hashMapSize() == hashMapSize()@pre + 1
     */
    void createOrder(Order order, List<MenuItem> menu, String userName);

    /**
     *
     * @pre criteria != null
     * @pre value !=null
     */
    List<MenuItem> searchProduct(String criteria, String value);




    void addProduct(MenuItem menuItem); //add product to menu item
    void deleteProduct(MenuItem menuItem);
    void modifyMenuItem  (MenuItem menuItem);

}
