package PresentationLayer;


import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;


public class ClientView extends JFrame {



    private final JComboBox searchProductsBox;
    private final JButton searchProductsBtn;
    private final JTextField searchProductsTextField;

    public JPanel mainPanel;
    public ProductsTable productsTable;



    private final JTextArea rightTextArea;
    private final JButton selectDateBtn;
    private final JButton selectProductBtn;
    private final JButton placeOrderBtn;
    private final JButton clearButton;


    public UtilDateModel model = new UtilDateModel();
    public JDatePanelImpl datePanel = new JDatePanelImpl(model);
    public JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

    public ClientView(){
        JLabel productsLabel;
        JPanel searchPanel;

        JPanel rightPanel;
        JScrollPane rightScrollPane;

        selectDateBtn=new JButton("Select Order Date");

        Border border = BorderFactory.createLineBorder(Color.BLACK,2);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(244, 186, 26));

        GridBagConstraints gbc=new GridBagConstraints();
        mainPanel=new JPanel(new GridBagLayout());


        mainPanel.setBackground(new Color(244, 186, 26));
        searchPanel=new JPanel();
        searchPanel.setBackground(new Color(244, 186, 26));

        productsLabel=new JLabel("Search products by:");
        productsLabel.setFont(new Font("Hello Sans",Font.BOLD,12));

        String criteria[] ={"name", "price", "rating", "fat", "calories" , "sodium" ,"protein"};
        searchProductsBox =new JComboBox(criteria);
        searchProductsBox.setFont(new Font("Hello Sans",Font.BOLD,12));
        searchProductsBox.setSelectedIndex(0);


        searchProductsTextField=new JTextField(25);
        searchProductsTextField.setText("Insert your text here...");
        searchProductsTextField.setFont(new Font("Hello Sans",Font.BOLD,12));
        searchProductsTextField.setPreferredSize(new Dimension(30,60));
        searchProductsTextField.setBorder(border);
        searchProductsTextField.setBackground(Color.LIGHT_GRAY);

        searchProductsTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                searchProductsTextField.setText(null); // Empty the text field when it receives focus
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(searchProductsTextField.getText().isEmpty()){
                    searchProductsTextField.setText("Insert your text here...");
                }else {
                    searchProductsTextField.setText(searchProductsTextField.getText());
                }
            }

        });

        searchProductsBtn=new JButton(new ImageIcon("src/main/java/PresentationLayer/icons/searchIcon.png"));
        searchProductsBtn.setBorder(border);
        searchProductsBtn.setBackground(new Color(244, 186, 26));

        searchPanel.add(productsLabel);
        searchPanel.add(searchProductsBox);
        searchPanel.add(searchProductsTextField);
        searchPanel.add(searchProductsBtn);

        placeOrderBtn=new JButton("Place Order");
        clearButton=new JButton("Clear");

        searchPanel.add(datePicker);
        searchPanel.add(selectDateBtn);
        searchPanel.add(placeOrderBtn);
        searchPanel.add(clearButton);

        add(searchPanel,BorderLayout.NORTH);


        productsLabel=new JLabel("Select the products you want to order:");
        productsLabel.setFont(new Font("Hello Sans",Font.BOLD,12));
        gbc.gridx=0;
        gbc.gridy=0;
        mainPanel.add(productsLabel,gbc);

        selectProductBtn=new JButton("Select Product");
        selectProductBtn.setPreferredSize(new Dimension(200,30));


        gbc.gridx=0;
        gbc.gridy=2;
        mainPanel.add(selectProductBtn,gbc);




        gbc.gridx=0;
        gbc.gridy=1;
        productsTable=new ProductsTable();
        productsTable.setPreferredSize(new Dimension(700,600));
        mainPanel.add(productsTable,gbc);


        add(mainPanel);


        
        rightPanel=new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(244, 186, 26));
        rightTextArea=new JTextArea();
        rightTextArea.setBackground(new Color(244, 186, 26));
        rightTextArea.setFont(new Font("Hello Sans",Font.BOLD,16));
        rightTextArea.setEditable(false);
        rightTextArea.setText("Order Details\n");


        rightScrollPane=new JScrollPane (rightTextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        rightScrollPane.setBorder(border);
        rightScrollPane.setPreferredSize(new Dimension(400,600));
        rightPanel.add(rightScrollPane,gbc);


        gbc.gridx=1;
        gbc.gridy=1;
        mainPanel.add(rightPanel,gbc);



        this.setSize(1200,800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }


    public void addProductSearchButtonListener(ActionListener a){

        searchProductsBtn.addActionListener(a);
    }


    public void addSelectProductButtonListener(ActionListener a){

        selectProductBtn.addActionListener(a);
    }

    public void addSelectOrderDateButtonListener(ActionListener a){

        selectDateBtn.addActionListener(a);
    }
    public void addPlaceOrderButtonListener(ActionListener a){

        placeOrderBtn.addActionListener(a);
    }

    public void addClearButtonListener(ActionListener a){

        clearButton.addActionListener(a);
    }
    public void addSearchCriteriaListener(ItemListener i){
        searchProductsBox.addItemListener(i);
    }

    public JTextField getSearchProductsTextField() {
        return searchProductsTextField;
    }

    public JComboBox getSearchProductsBox() {
        return searchProductsBox;
    }

    public JTextArea getRightTextArea() {
        return rightTextArea;
    }



}
