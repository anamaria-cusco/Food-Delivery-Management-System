package PresentationLayer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;


public class AdminView extends JFrame{

        public  ProductsTable viewAllPanel;
        public  ProductsTable menuItemPanel;
        public JButton generateReportsBtn;
        public JButton importProductsBtn;
        public JButton addProductBtn;
        public JButton deleteProductBtn;
        public JButton modifyMenuItemBtn;
        public JButton deleteMenuItemBtn;
        public JButton createMenuItemBtn;

        public AdminView() {

            JPanel mainPanel;
            JPanel downPanel;
            JPanel rightPanel;
            JPanel buttonsPanel;


            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            flowLayout.setHgap(100);
            mainPanel = new JPanel(new BorderLayout());
            downPanel = new JPanel(flowLayout);
            rightPanel = new JPanel(new GridLayout(3,1));
            buttonsPanel =new JPanel(new FlowLayout());

            downPanel.setBackground(Color.LIGHT_GRAY);

            Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

            JLabel menuItemLabel = new JLabel("Please select the products for a new Menu Item:");
            menuItemLabel.setFont(new Font("Hello Sans", Font.BOLD, 18));

            menuItemPanel= new ProductsTable();

            importProductsBtn = new JButton("Import Products");
            importProductsBtn.setBorder(border);
            importProductsBtn.setBackground(new Color(244, 186, 26));
            importProductsBtn.setFont(new Font("Hello Sans", Font.BOLD, 18));


            generateReportsBtn = new JButton("Generate Reports");
            generateReportsBtn.setBorder(border);
            generateReportsBtn.setBackground(new Color(244, 186, 26));
            generateReportsBtn.setFont(new Font("Hello Sans", Font.BOLD, 18));


            modifyMenuItemBtn = new JButton("Modify Menu Item");
            modifyMenuItemBtn.setBorder(border);
            modifyMenuItemBtn.setBackground(new Color(244, 186, 26));
            modifyMenuItemBtn.setFont(new Font("Hello Sans", Font.BOLD, 18));



            deleteMenuItemBtn = new JButton("Delete Menu Item ");
            deleteMenuItemBtn.setBorder(border);
            deleteMenuItemBtn.setBackground(new Color(244, 186, 26));
            deleteMenuItemBtn.setFont(new Font("Hello Sans", Font.BOLD, 18));

            createMenuItemBtn = new JButton("Create New Menu Item");
            createMenuItemBtn.setBorder(border);
            createMenuItemBtn.setBackground(new Color(244, 186, 26));
            createMenuItemBtn.setFont(new Font("Hello Sans", Font.BOLD, 18));

            downPanel.add(importProductsBtn);
            downPanel.add(generateReportsBtn);
            downPanel.add(deleteMenuItemBtn);
            downPanel.add(modifyMenuItemBtn);
            downPanel.add(createMenuItemBtn);


            addProductBtn = new JButton("Add Product");
            addProductBtn.setBorder(border);
            addProductBtn.setBackground(new Color(244, 186, 26));
            addProductBtn.setFont(new Font("Hello Sans", Font.BOLD, 18));


            deleteProductBtn = new JButton("Delete Product ");
            deleteProductBtn.setBorder(border);
            deleteProductBtn.setBackground(new Color(244, 186, 26));
            deleteProductBtn.setFont(new Font("Hello Sans", Font.BOLD, 18));



            buttonsPanel.add(addProductBtn);
            buttonsPanel.add(deleteProductBtn);

            rightPanel.add(menuItemLabel);
            rightPanel.add(menuItemPanel);
            rightPanel.add(buttonsPanel);



            viewAllPanel = new ProductsTable();


            mainPanel.add(viewAllPanel, BorderLayout.CENTER);
            mainPanel.add(downPanel, BorderLayout.SOUTH);
            mainPanel.add(rightPanel, BorderLayout.EAST);


            this.setContentPane(mainPanel);
            this.setSize(1400, 800);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        }

        public void addAddProductListener(ActionListener a){
            addProductBtn.addActionListener(a);
        }


        public void addDeleteProductListener(ActionListener a){
            deleteProductBtn.addActionListener(a);
        }
        public void addViewAllProductsListener(ActionListener a){
            importProductsBtn.addActionListener(a);
        }

        public void addGenerateReportsListener(ActionListener a){
            generateReportsBtn.addActionListener(a);
        }

        public void addDeleteMenuItemListener(ActionListener a){
            deleteMenuItemBtn.addActionListener(a);
        }

        public void addModifyMenuItemListener(ActionListener a){
            modifyMenuItemBtn.addActionListener(a);
        }
        public void addCreateNewMenuItemListener(ActionListener a){
            createMenuItemBtn.addActionListener(a);
        }




}
