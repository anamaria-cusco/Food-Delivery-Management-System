package DataLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Serializator {
    public static void serializeUser(List<User> users){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("users.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static List<User> deserializeUser(){
        ArrayList<User> users = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("users.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            users = (ArrayList<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e){ e.printStackTrace(); }

        return users;
    }




    public static void serializeMenuItem(List<MenuItem> menuItems){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("menuItems.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(menuItems);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch ( IOException e){
            e.printStackTrace();
        }
    }

    public static List<MenuItem> deserializeMenuItem(){
        List<MenuItem> menuItems = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("menuItems.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            menuItems = (ArrayList<MenuItem>) objectInputStream.readObject();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();

        }
        catch (IOException e){ e.printStackTrace(); }
        catch (ClassNotFoundException e){ e.printStackTrace(); }

        return menuItems;
    }



    public static void serializeOrder(HashMap<Order,List<MenuItem>> orders){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("orders.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(orders);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch ( IOException e){
            e.printStackTrace();
        }
    }

    public static HashMap<Order,List<MenuItem>> deserializeOrder(){
        HashMap<Order,List<MenuItem>> orders = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("orders.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            orders = (HashMap<Order,List<MenuItem>>) objectInputStream.readObject();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();

        }
        catch (IOException e){ e.printStackTrace(); }
        catch (ClassNotFoundException e){ e.printStackTrace(); }

        return orders;
    }

}
