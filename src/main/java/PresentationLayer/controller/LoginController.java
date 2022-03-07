package PresentationLayer.controller;

import BusinessLayer.DeliveryService;
import DataLayer.Serializator;
import DataLayer.User;
import PresentationLayer.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class LoginController {

    private List<User> users;
    private final LoginView view;
    private final DeliveryService deliveryService;


    public LoginController(LoginView view) {
        users= Serializator.deserializeUser();
        this.view = view;
        this.deliveryService = new DeliveryService();
        view.addLoginButtonListener(new LoginButtonListener());
        view.addRegisterButtonListener(new RegisterButtonListener());
        view.addCancelButtonListener(new CancelButtonListener());

    }
    private User findUser(String username, String password){

        return users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst().orElse(null);

    }

    private class LoginButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsernameFieldText();
            String password = view.getPasswordFieldText();

            User user = findUser(username,password);
            System.out.println(user);
            if(user!=null){

                view.setUsernameFieldText("");
                view.setPasswordFieldText("");
                switch(user.getUserRole()) {
                    case ADMIN:{
                        new AdminController(user,deliveryService);

                        break;
                    }
                    case CLIENT:{
                        new ClientController(user,deliveryService);
                        break;
                    }
                    case EMPLOYEE:{
                        new EmployeeController(user,deliveryService);
                        break;
                    }
                    default:break;
                }

            }
            else{
                LoginView.ErrorMessage("Invalid username or password!");
            }

        }
    }
    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsernameFieldText();
            String password = view.getPasswordFieldText();
            if(password.isEmpty() || username.isEmpty()){
                LoginView.ErrorMessage("Username or password EMPTY!");
            }else {

                User newUser = new User(users.get(users.size()-1).getId()+1,username, password);
                users.add(newUser);
                Serializator.serializeUser(users);
                JOptionPane.showMessageDialog(view, "You've successfully registered!");
            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setUsernameFieldText("");
            view.setPasswordFieldText("");
        }
    }
    public LoginView getView() {
        return view;
    }

}
