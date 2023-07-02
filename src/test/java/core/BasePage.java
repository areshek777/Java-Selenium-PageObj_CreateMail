package core;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

abstract public class BasePage {
    protected static WebDriver driver;
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String email;
    private static String name;
    private static String surname;
    private static String username;
    private static String password;

    public static void loadResources() throws IOException {
        configFile = new FileInputStream("src/test/resources/my.properties");
        prop.load(configFile);

        email = prop.getProperty("email");
        name = prop.getProperty("name");
        surname = prop.getProperty("surname");
        username = prop.getProperty("username");
        password = prop.getProperty("password");
    }

    public static void setUp(WebDriver webDriver) throws IOException {
        driver = webDriver;
    }

    public static String getEmail() {
        return email;
    }

    public static String getName() {
        return name;
    }

    public static String getSurname() {
        return surname;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
