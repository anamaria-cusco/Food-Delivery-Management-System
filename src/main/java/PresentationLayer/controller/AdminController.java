package PresentationLayer.controller;



import BusinessLayer.*;
import DataLayer.FileWriterClass;
import DataLayer.User;
import PresentationLayer.AdminView;
import PresentationLayer.ProductsTable;
import PresentationLayer.ReportsFrame;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AdminController {
    private ReportsFrame reportsFrame;
    private Date selectedDate;

    private AdminView view;
    private DeliveryService deliveryService;
    private List<MenuItem> menuItemComponents= new ArrayList<>();

    public AdminController(User user, DeliveryService deliveryService) {


        this.deliveryService = deliveryService;
        view = new AdminView();
        view.addAddProductListener(new AddProductListener());
        view.addDeleteProductListener(new DeleteProductListener());
        view.addViewAllProductsListener(new ViewAllProductsListener());
        view.addCreateNewMenuItemListener(new CreateNewMenuItemListener());
        view.addDeleteMenuItemListener(new DeleteMenuItemListener());
        view.addModifyMenuItemListener(new ModifyMenuItemListener());
        view.addGenerateReportsListener(new GenerateReportsListener());
        view.setVisible(true);
        displayProducts(view.viewAllPanel,deliveryService.getMenuItems());




    }


    public void displayProducts(ProductsTable productsTable,  List<MenuItem> menuItems){

        if(menuItems!=null) {
           productsTable.productsList = menuItems;
           productsTable.tableModel.setObjectRows(menuItems);
           productsTable.refresh();
           productsTable.revalidate();
           productsTable.repaint();
        }
        else{
            JOptionPane.showMessageDialog(view,"No products yet. Please select IMPORT option!");
        }
    }
    class ViewAllProductsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.importProducts();
            displayProducts(view.viewAllPanel,deliveryService.getMenuItems());
        }
    }

    class CreateNewMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            MenuItem newMenuItem =new CompositeProduct(menuItemComponents);
            deliveryService.createMenuItem(newMenuItem);
            menuItemComponents = new ArrayList<>();

        }
    }

    class DeleteMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow=view.viewAllPanel.table.getSelectedRow();
            MenuItem menuItem =view.viewAllPanel.tableModel.getObjectFromSelectedRow(selectedRow);
            if( menuItem instanceof BaseProduct) {
                JOptionPane.showMessageDialog(view, "You can't delete a Base Product!");
            }
            else{
                deliveryService.deleteMenuItem(menuItem);
                displayProducts(view.viewAllPanel, deliveryService.getMenuItems());
            }
        }
    }

    class ModifyMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow=view.viewAllPanel.table.getSelectedRow();
           MenuItem menuItem =view.viewAllPanel.tableModel.getObjectFromSelectedRow(selectedRow);
           if( menuItem instanceof BaseProduct) {
               JOptionPane.showMessageDialog(view, "You can't modify a Base Product!");
           }
           else{
               displayProducts(view.menuItemPanel, ((CompositeProduct) menuItem).getComponents());
               deliveryService.deleteMenuItem(menuItem);
               menuItemComponents = ((CompositeProduct) menuItem).getComponents();

           }

        }
    }



    class AddProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow=view.viewAllPanel.table.getSelectedRow();
            menuItemComponents.add(view.viewAllPanel.tableModel.getObjectFromSelectedRow(selectedRow));
            displayProducts(view.menuItemPanel, menuItemComponents);
        }
    }

    class DeleteProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

                int selectedRow = view.menuItemPanel.table.getSelectedRow();
                menuItemComponents.remove(view.menuItemPanel.tableModel.getObjectFromSelectedRow(selectedRow));
                displayProducts(view.menuItemPanel, menuItemComponents);
            }
    }

    class GenerateReportsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            reportsFrame = new ReportsFrame();

            reportsFrame.addGenerateReport0Listener(new GenerateReport0Listener());
            reportsFrame.addGenerateReport1Listener(new GenerateReport1Listener());
            reportsFrame.addGenerateReport2Listener(new GenerateReport2Listener());
            reportsFrame.addGenerateReport3Listener(new GenerateReport3Listener());
            reportsFrame.addSelectDateListener(new SelectDateListener());
        }
    }

    class GenerateReport0Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String startHour = reportsFrame.getStartHourTextField().getText();
            String endHour = reportsFrame.getEndHourTextField().getText();

           Map<Order, List<MenuItem>> products= deliveryService.generateReport0(Integer.parseInt(startHour),Integer.parseInt(endHour));
           FileWriterClass.printReport(products);
        }
    }

    class GenerateReport1Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String noOfTimes = reportsFrame.getNoOfTimesTextField1().getText();
            List<MenuItem> products = deliveryService.generateReport1(Integer.parseInt(noOfTimes));
            FileWriterClass.printReport(products);

        }
    }

    class GenerateReport2Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String noOfTimes = reportsFrame.getNoOfTimesTextField2().getText();
            String amount = reportsFrame.getAmountTextField().getText();
            List<User> users=deliveryService.generateReport2(Integer.parseInt(noOfTimes),Integer.parseInt(amount));
            FileWriterClass.printReport(users);
        }
    }

    class GenerateReport3Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           Map<MenuItem,Long> products= deliveryService.generateReport3(selectedDate);
           FileWriterClass.printReport(products);
        }
    }
    class SelectDateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedDate = (Date) reportsFrame.datePicker.getModel().getValue();
        }
    }


}
