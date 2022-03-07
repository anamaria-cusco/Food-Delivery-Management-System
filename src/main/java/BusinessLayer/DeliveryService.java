package BusinessLayer;


import DataLayer.FileWriterClass;
import DataLayer.Serializator;
import DataLayer.User;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService  extends Observable implements IDeliveryServiceProcessing, Serializable {

    private List<MenuItem> menuItems;
    private HashMap<Order, List<MenuItem>> orders;

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public DeliveryService() {


        this.menuItems = Serializator.deserializeMenuItem();
        try {
            this.orders = Serializator.deserializeOrder();
        }catch (Exception e){
            orders = new HashMap<>();
        }
    }

    public HashMap<Order, List<MenuItem>> getOrders() {
        return orders;
    }


    @Override
    public void importProducts() {
        List<MenuItem> products=new ArrayList<>();
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader("products.csv"));
            csvReader.lines().skip(1).distinct().map(line->Arrays.asList(line.split(",")))
                    .forEach(list -> products.add(new BaseProduct(list.get(0),
                            Double.parseDouble(list.get(1)),
                            Integer.parseInt(list.get(2)),
                            Integer.parseInt(list.get(3)),
                            Integer.parseInt(list.get(4)),
                            Integer.parseInt(list.get(5)),
                            Integer.parseInt(list.get(6)))));
            csvReader.close();
            menuItems = products.stream().distinct().collect(Collectors.toList());
            Serializator.serializeMenuItem(menuItems);
            assert menuItems.size()>0:"We should have at least one product in the MENU!";
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addProduct(MenuItem menuItem) {
        assert menuItem.getPrice() > 0.0f : "Price must be positive!";
        assert menuItem != null : "The new element can't be null!";

        CompositeProduct item = (CompositeProduct) menuItem;
        int oldSize =item.components.size();
        item.components.add(menuItem);
        assert oldSize == item.components.size() + 1 : "The new size must be the old size + 1!";
    }

    @Override
    public void deleteProduct(MenuItem menuItem) {
        assert menuItem != null : "The element which is going to be deleted can't be null!";
        CompositeProduct item = (CompositeProduct) menuItem;
        item.components.removeIf(m -> m.equals(menuItem));
    }

    @Override
    public void deleteMenuItem(MenuItem menuItem) {
        assert menuItem != null : "The menu item which is going to be deleted can't be null!";
        menuItems.remove(menuItem);
        Serializator.serializeMenuItem(this.menuItems);

    }

    @Override
    public void modifyMenuItem(MenuItem menuItem) {
        assert menuItem != null : "The element which is going to be modified can't be null!";
        menuItem.setName(menuItem.getName());
        menuItem.setPrice(menuItem.getPrice());

    }

    @Override
    public void createMenuItem(MenuItem menuItem) {
        assert menuItem!=null: "The new menu item should be valid!";
        assert menuItem.getPrice()>0.0f: "The price of the new menu item should be posititve!";

        menuItems.add(menuItem);
        Serializator.serializeMenuItem(this.menuItems);
    }

    public static Map<MenuItem, Long>  productsFrequencyMap(Stream<MenuItem> elements) {
        return elements.collect(
                Collectors.groupingBy(
                        Function.identity(),
                        HashMap::new,
                        Collectors.counting()
                )
        );
    }

    //TODO time interval of the orders
    public  Map<Order, List<MenuItem>> generateReport0(int startHour, int endHour) {
        assert startHour>=0 && startHour<=23:"Start hour must be a valid hour!";
        assert endHour>=0 && endHour<=23:"Start hour must be a valid hour!";
        System.out.println(startHour +" "+endHour);
        return  orders.entrySet().stream()
                .filter(entry -> {
                    int hour  = entry.getKey().getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalTime().getHour();
                    return hour >= startHour && hour <= endHour;
                }).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

    }




    // TODO the products ordered more than a specified number of times so far.


    public List<MenuItem> generateReport1(int inputValue){
        assert inputValue>0: "The number of times the clients ordered should be at least 1!";

        Map<MenuItem, Long> map=productsFrequencyMap(orders.values().stream().flatMap(List::stream));
        System.out.println(map);
        return map.entrySet().stream()
                .filter(entry -> entry.getValue() >= inputValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }


    // TODO The clients that have ordered more than a specified number of times and the value of the order was higher than a specified amount".

    public List<User> generateReport2(int inputValue, int amount){
        assert inputValue>0:"The number of times the clients ordered should be at least 1!";
        assert amount>0:"The amount of the order should be a positive value!";

        List<Order> ordersList=orders.entrySet().stream()
                            .filter(entry -> computeOrderPrice(entry.getValue()) >= amount)
                            .map(Map.Entry::getKey).collect(Collectors.toList());

        List<Integer> clientIDs = ordersList.stream().collect(Collectors.groupingBy(Order::getClientID)).
                entrySet().stream().
                filter(entry -> entry.getValue().size()>= inputValue).
                map(Map.Entry::getKey).
                collect(Collectors.toList());

        List<User> users = Serializator.deserializeUser().stream()
                .filter(user -> clientIDs.contains(user.getId()))
                .collect(Collectors.toList());

        return users;

    }

    //TODO the products ordered within a specified day with the number of times they have been ordered.
    public Map<MenuItem,Long> generateReport3(Date selectedDate) {
        assert selectedDate!=null: "Selected date can't be null!";
        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        return  productsFrequencyMap(orders.entrySet().stream()
                .filter(entry -> {
                    return entry.getKey().getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(localDate);
                }).map(Map.Entry::getValue).flatMap(List::stream));



    }
    public static double computeOrderPrice(List<MenuItem> menu){
        assert menu!=null:"The menu can't be null. You have to add some products first in order to compute the order price!";
        return menu.stream().mapToDouble(MenuItem::computePrice).sum();
    }

    @Override
    public void createOrder(Order order, List<MenuItem> menu, String userName) {
        assert order != null: " The new order can't be null!";
        assert menuItems.size() >= 1 : " You need to add at least one item!";
        orders.put(order, menu);

        StringBuilder message =new StringBuilder();
        message.append("A new order has been placed!\n");
        message.append("ORDER DETAILS\n");
        message.append(order);
        message.append("\n");

        setChanged();
        notifyObservers(message);
        double totalPrice= computeOrderPrice(menu);
        FileWriterClass.printBill(order, userName, menu, totalPrice);
        Serializator.serializeOrder(orders);
    }

    @Override
    public List<MenuItem> searchProduct(String criteria, String value) {
        assert criteria!=null: "The searching criteria can't be null";
        assert value!=null:"You must specify a value for the selected criteria in order to search for a product!";

        int toleranceValue = 10;

        switch (criteria) {
            case "name":
                return menuItems.stream().filter(p -> p.getName().contains(value)).collect(Collectors.toList());
            case "price":
                double price = Double.parseDouble(value);
                return menuItems.stream().filter(p -> (p.getPrice() >= price - toleranceValue && p.getPrice() <= price + toleranceValue)).collect(Collectors.toList());
            case "rating":
                double rating = Double.parseDouble(value);
                return menuItems.stream().filter(p -> p.getRating() >= rating).collect(Collectors.toList());
            case "fat":
                int fat = Integer.parseInt(value);
                return menuItems.stream().filter(p ->  p.getFat() == fat ).collect(Collectors.toList());
            case "sodium":
                int sodium = Integer.parseInt(value);
                return menuItems.stream().filter(p -> (p.getSodium() >= sodium - toleranceValue && p.getSodium() <= sodium + toleranceValue)).collect(Collectors.toList());
            case "protein":
                int protein = Integer.parseInt(value);
                return menuItems.stream().filter(p -> p.getProtein() == protein ).collect(Collectors.toList());
            case "calories":
                int calories = Integer.parseInt(value);
                return menuItems.stream().filter(p -> (p.getCalories() >= calories - toleranceValue && p.getCalories() <= calories + toleranceValue)).collect(Collectors.toList());
            default:return null;
        }

    }

    public List<User> getUsers(){
        return Serializator.deserializeUser();
    }

    public int getLastOrderID(){
        return orders.entrySet().stream().max((entry1, entry2) -> entry1.getKey().getOrderID() > entry2.getKey().getOrderID() ? 1 : -1).get().getKey().getOrderID();
    }

    public boolean checkForEmptyOrders() {

        for (Map.Entry<Order, List<MenuItem>> orderListEntry : orders.entrySet()) {
            if (orderListEntry.getValue() == null)
                return true;
        }

        return false;
    }


    /**
     * Invariant - this conditions should always be true
     * @return
     */
    public boolean isWellFormed() {
        if (getUsers() == null) //It impossible to have 0 USERS
            return false;
        if (menuItems == null) //We can't have an empty menu
            return false;
        if (getLastOrderID()<0) //The last order ID should be a positive value
            return false;
        if (checkForEmptyOrders()) //Can;t have empty orders
            return false;
        return true;
    }




}
