package PresentationLayer;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class ReportsFrame extends JFrame {
    private JButton selectDateBtn;
    private JPanel mainPanel;

    private JLabel reportTitle;
    private String[] criteria= {">> Time interval of the orders:",
                        ">> The products ordered more than a specified number of times so far:",
                        ">> The clients that have ordered more than a specified number of times and the value of the order was higher than a specified amount:",
                        ">> The products ordered within a specified day with the number of times they have been ordered:"};


    private JButton button0;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField startHourTextField;
    private JTextField endHourTextField;
    private JTextField noOfTimesTextField1;
    private JTextField noOfTimesTextField2;
    private JTextField amountTextField;
    public UtilDateModel model = new UtilDateModel();
    public JDatePanelImpl datePanel = new JDatePanelImpl(model);
    public JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

    public ReportsFrame(){
        mainPanel=new JPanel(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 10, 10, 10);


        reportTitle=new JLabel("Reports");
        reportTitle.setFont(new Font("Tahoma",Font.BOLD,18));

        JLabel label0 =new JLabel(criteria[0]);
        JLabel label1 =new JLabel(criteria[1]);
        JLabel label2 =new JLabel(criteria[2]);
        JLabel label3 =new JLabel(criteria[3]);

        button0=new JButton("Generate Report");
        button1=new JButton("Generate Report");
        button2=new JButton("Generate Report");
        button3=new JButton("Generate Report");

        JPanel label0Panel=new JPanel(new FlowLayout());
        JLabel startHourLabel =new JLabel("Start Hour:");
        JLabel endHourLabel =new JLabel("End Hour:");
        startHourTextField=new JTextField(5);
        endHourTextField=new JTextField(5);

        label0Panel.add(startHourLabel);
        label0Panel.add(startHourTextField);
        label0Panel.add(endHourLabel);
        label0Panel.add(endHourTextField);



        JPanel label1Panel=new JPanel(new FlowLayout());
        JLabel noOfTimesLabel1 = new JLabel("No. of times:");
        noOfTimesTextField1=new JTextField(5);
        label1Panel.add(noOfTimesLabel1);
        label1Panel.add(noOfTimesTextField1);


        JPanel label2Panel=new JPanel(new FlowLayout());
        JLabel noOfTimesLabel2=  new JLabel("No. of times:");
        noOfTimesTextField2=new JTextField(5);
        JLabel amountLabel = new JLabel("Amount:");
        amountTextField=new JTextField(5);
        label2Panel.add(noOfTimesLabel2);
        label2Panel.add(noOfTimesTextField2);
        label2Panel.add(amountLabel);
        label2Panel.add(amountTextField);

        selectDateBtn=new JButton("Select Date");
        JPanel label3Panel =new JPanel();
        JLabel dayLabel =  new JLabel("Day:");
        label3Panel.add(dayLabel);
        label3Panel.add(datePicker);
        label3Panel.add(selectDateBtn);


        label0Panel.setBackground(new Color(244, 186, 26));
        label1Panel.setBackground(new Color(244, 186, 26));
        label2Panel.setBackground(new Color(244, 186, 26));
        label3Panel.setBackground(new Color(244, 186, 26));



        gbc.gridx=0;
        gbc.gridy=0;
        mainPanel.add(reportTitle,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        mainPanel.add(label0,gbc);


        gbc.gridx=1;
        gbc.gridy=2;
        mainPanel.add(button0,gbc);


        gbc.gridx=0;
        gbc.gridy=3;
        mainPanel.add(label0Panel,gbc);

        gbc.gridx=0;
        gbc.gridy=4;
        mainPanel.add(label1,gbc);

        gbc.gridx=1;
        gbc.gridy=4;
        mainPanel.add(button1,gbc);

        gbc.gridx=0;
        gbc.gridy=5;
        mainPanel.add(label1Panel,gbc);

        gbc.gridx=0;
        gbc.gridy=6;
        mainPanel.add(label2,gbc);

        gbc.gridx=1;
        gbc.gridy=6;
        mainPanel.add(button2,gbc);

        gbc.gridx=0;
        gbc.gridy=7;
        mainPanel.add(label2Panel,gbc);


        gbc.gridx=0;
        gbc.gridy=8;
        mainPanel.add(label3,gbc);

        gbc.gridx=1;
        gbc.gridy=8;
        mainPanel.add(button3,gbc);

        gbc.gridx=0;
        gbc.gridy=9;
        mainPanel.add(label3Panel,gbc);

         this.setContentPane(mainPanel);
         this.setSize(new Dimension(1000,600));
         mainPanel.setBackground(new Color(244, 186, 26));
         this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         this.setVisible(true);

    }

    public void addGenerateReport0Listener(ActionListener a){
        button0.addActionListener(a);
    }
    public void addGenerateReport1Listener(ActionListener a){
        button1.addActionListener(a);
    }
    public void addGenerateReport2Listener(ActionListener a){
        button2.addActionListener(a);
    }
    public void addGenerateReport3Listener(ActionListener a){
        button3.addActionListener(a);
    }
    public void addSelectDateListener(ActionListener a){
        selectDateBtn.addActionListener(a);
    }

    public JTextField getStartHourTextField() {
        return startHourTextField;
    }

    public JTextField getEndHourTextField() {
        return endHourTextField;
    }

    public JTextField getNoOfTimesTextField1() {
        return noOfTimesTextField1;
    }

    public JTextField getNoOfTimesTextField2() {
        return noOfTimesTextField2;
    }

    public JTextField getAmountTextField() {
        return amountTextField;
    }

    public static void main(String[] args) {
        new ReportsFrame();
    }
}
