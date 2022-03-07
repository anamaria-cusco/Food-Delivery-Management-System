package DataLayer;

import java.util.ArrayList;
import java.util.List;

public class UserDummy {
    public static void main(String[] args) {
        User client1=new User(1,"popescu","client",UserRole.CLIENT);
        User client2=new User(2,"ionescu","client",UserRole.CLIENT);
        User admin=new User(3,"admin","admin",UserRole.ADMIN);
        User angajat1=new User(4,"user","user",UserRole.EMPLOYEE);

        List<User> users=new ArrayList<User>();
        users.add(client1);
        users.add(client2);
        users.add(admin);
        users.add(angajat1);
        System.out.println(users);
        /*
        User.serializeUser(users);
        List<User> userList = User.deserializeUser();
        System.out.println(userList);*/
    }
}
