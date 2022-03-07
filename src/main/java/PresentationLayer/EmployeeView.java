package PresentationLayer;


import BusinessLayer.DeliveryService;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class EmployeeView extends JFrame implements Observer {

    private JLabel titleLabel;
    private JPanel mainPanel;
    private JScrollPane mainScrollPane;
    private final JTextArea mainTextArea;

    
    public EmployeeView(){


        Border border = BorderFactory.createLineBorder(Color.BLACK,2);

        titleLabel=new JLabel("ORDERS");
        titleLabel.setFont(new Font("Hello Sans", Font.BOLD, 24));

        mainPanel=new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(244, 186, 26));
        mainTextArea=new JTextArea();
        mainTextArea.setBackground(new Color(244, 186, 26));
        mainTextArea.setFont(new Font("Hello Sans",Font.BOLD,16));
        mainTextArea.setEditable(false);



        mainScrollPane=new JScrollPane (mainTextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainScrollPane.setBorder(border);
        mainScrollPane.setPreferredSize(new Dimension(400,600));
        mainPanel.add(titleLabel,BorderLayout.PAGE_START);
        mainPanel.add(mainScrollPane,BorderLayout.CENTER);
        this.setContentPane(mainPanel);
        this.setSize(800,600);

    }


    @Override
    public void update(Observable o, Object arg) {
        mainTextArea.append(arg.toString());
        mainPanel.repaint();
        mainPanel.revalidate();

    }


}
