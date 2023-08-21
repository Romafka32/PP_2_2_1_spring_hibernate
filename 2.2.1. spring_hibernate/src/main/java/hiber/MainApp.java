package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Миша", "Болгов", "user1@mail.ru", new Car("Tesla", 123)));
      userService.add(new User("Маша", "Малышева", "user2@mail.ru", new Car("Toyota", 321)));
      userService.add(new User("Роман", "Кузнецов", "user3@mail.ru", new Car("Mazda", 771)));
      userService.add(new User("Тимур", "Тыжинов", "user4@mail.ru", new Car("VW", 148)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Car serial = "+user.getCar().getSeries());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Mazda",771));

      context.close();
   }
}
