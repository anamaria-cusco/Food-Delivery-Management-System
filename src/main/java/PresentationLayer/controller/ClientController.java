package PresentationLayer.controller;


import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import DataLayer.User;
import PresentationLayer.AdminView;
import PresentationLayer.ClientView;
import PresentationLayer.ProductsTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ClientController {
    private  ClientView view;
    private  DeliveryService deliveryService;
    private List<MenuItem> foundProducts = new ArrayList<>();
    private List<MenuItem> orderedProducts = new ArrayList<>();
    private Date selectedDate;
    private String selectedCriteria;
    private String orderDetails="";
    private User user;



    public ClientController(User user, DeliveryService deliveryService) {
            this.user=user;
            this.deliveryService = deliveryService;
            view = new ClientView();
            view.addPlaceOrderButtonListener(new PlaceOrderListener());
            view.addClearButtonListener(new ClearListener() );
            view.addProductSearchButtonListener(new ProductSearchButtonListener());
            view.addSelectOrderDateButtonListener(new SelectOrderDateButtonListener());
            view.addSelectProductButtonListener(new SelectProductButtonListener());
            view.addSearchCriteriaListener(new SearchCriteriaListener());

            displayOrderDetails();
            view.getRightTextArea().setText(orderDetails);
            view.setVisible(true);

    }

    public void displayProducts(List<MenuItem> menuItems){

        if(menuItems!=null) {
            view.productsTable.productsList = menuItems;
            view.productsTable.tableModel.setObjectRows(menuItems);
            view.productsTable.refresh();
            view.productsTable.revalidate();
            view.repaint();
        }
        else{
            JOptionPane.showMessageDialog(view,"No products found. Please select another option!");
        }
    }

    public void displayOrderDetails(){
        orderDetails+=String.format("ORDER DETAILS\n");
        orderDetails+= String.format("Client Username: %s\n",user.getUsername());
        orderDetails+= String.format("Client ID: %s\n", user.getId());
        orderDetails+=String.format("\nOrdered products:\n");
        orderDetails+=String.format("%s %s\n","Name","Price");
    }
    class PlaceOrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(selectedDate==null){
                JOptionPane.showMessageDialog(view, "Please select an order date!");
            }else if (foundProducts.isEmpty()){
                JOptionPane.showMessageDialog(view, "Please choose some products first!");
            }
            else{
                int orderID = deliveryService.getOrders().size() + 1;
                Order newOrder= new Order(orderID, user.getId(), selectedDate);
                deliveryService.createOrder(newOrder,orderedProducts, user.getUsername());
                double totalPrice= DeliveryService.computeOrderPrice(orderedProducts);
                orderDetails+=String.format("\nTotal Price: %.1f",totalPrice);
                orderDetails+=String.format("\nOrder ID: %s ",orderID);
                orderDetails+=String.format("\nOrder date: %s ",selectedDate);
                view.getRightTextArea().setText(orderDetails);
                orderedProducts= new ArrayList<>();

            }

        }
    }

    class ProductSearchButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(selectedCriteria == null) {
                selectedCriteria = (String)view.getSearchProductsBox().getSelectedItem();
            }
            String value = view.getSearchProductsTextField().getText();
            foundProducts = deliveryService.searchProduct(selectedCriteria,value);
            displayProducts(foundProducts);
        }
    }

    class SelectOrderDateButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedDate = (java.util.Date) view.datePicker.getModel().getValue();
        }
    }

    class SelectProductButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow= view.productsTable.table.getSelectedRow();
            MenuItem selectedItem = view.productsTable.tableModel.getObjectFromSelectedRow(selectedRow);
            orderDetails+=String.format("%s %.1f\n",selectedItem.getName(),selectedItem.getPrice());
            view.getRightTextArea().setText(orderDetails);
            orderedProducts.add(selectedItem);
        }
    }

    class SearchCriteriaListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            selectedCriteria = (String)view.getSearchProductsBox().getSelectedItem();
        }
    }


    class ClearListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            orderDetails="";
            displayOrderDetails();
            view.getRightTextArea().setText(orderDetails);
        }
    }


}
