package restassured_tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class Faker_demo {

    @Test
    void testGenerateDummyData()
    {
        Faker faker = new Faker();

        String full_name = faker.name().fullName();
        String first_name = faker.name().firstName();
        String last_name = faker.name().lastName();
        String user_name = faker.name().username();
        String password = faker.internet().password();
        String phone_number = faker.phoneNumber().cellPhone();
        String email = faker.internet().safeEmailAddress();

        System.out.println("Full Name: " + full_name);
        System.out.println("First Name: " + first_name);
        System.out.println("Last Name: " + last_name);
        System.out.println("User Name: " + user_name);
        System.out.println("Password: " + password);
        System.out.println("Phone Number: " + phone_number);
        System.out.println("Email: " + email);
    }
}
