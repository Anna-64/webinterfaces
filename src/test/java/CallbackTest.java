import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {
    private WebDriver driver;

//    @BeforeAll
//    public static void setUpAll(){
//        System.setProperty("webdriver.chrome.driver","./driver/win/chromedriver.exe");
//
//    }

    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
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
        driver.get("http://localhost:7777");
//        List<WebElement> elements = driver.findElements(By.cssSelector("input.input__control"));
//        elements.get(0).sendKeys("Абъдуллаев Ян");
//        elements.get(1).sendKeys("+79003333333");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Абъдуллаев Ян");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79003333333");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        System.out.println();

       String actualText = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
       String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";

        assertEquals (expected, actualText);

    }
}
