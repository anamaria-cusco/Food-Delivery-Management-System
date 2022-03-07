package PresentationLayer.controller;



import BusinessLayer.DeliveryService;
import DataLayer.User;
import PresentationLayer.EmployeeView;

import javax.swing.*;

public class EmployeeController {
   private EmployeeView view;
    private DeliveryService deliveryService;
    public EmployeeController(User user, DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        view = new EmployeeView();
        this.deliveryService.addObserver(view);
        view.setVisible(true);
    }
}
