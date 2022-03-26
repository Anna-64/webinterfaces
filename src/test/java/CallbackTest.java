import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    public static void setUpAll(){
//        System.setProperty("webdriver.chrome.driver","./driver/win/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @AfterEach
    public void tearDown(){
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldSendForm(){
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.cssSelector("input.input__control"));
        elements.get(0).sendKeys("Абъдуллаев Ян");
        elements.get(1).sendKeys("+79003333333");
//       driver.findElement(By.cssSelector("[type=`text`]")).sendKeys ("Абъдуллаев Ян");
 //      driver.findElement(By.cssSelector("[type=`tel`]")).sendKeys("+79003333333");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        System.out.println();

       String actualText = driver.findElement(By.className("paragraph")).getText().trim();
       String expected = ("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.");

        assertEquals (expected, actualText);

    }
}
