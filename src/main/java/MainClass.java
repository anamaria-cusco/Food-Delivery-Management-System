import PresentationLayer.LoginView;
import PresentationLayer.controller.LoginController;

public class MainClass {
    public static void main(String[] args) {
        new LoginController(new LoginView());
    }
}
