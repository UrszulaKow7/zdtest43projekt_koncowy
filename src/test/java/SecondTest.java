import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
class LoginTests

{
    WebDriver driver;
    String username = "tomsmith", password = "SuperSecretPassword!";
    String expectedMsg = "You logged into a secure area!";
    @Before
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Test
    public void moreCodeLoginTest() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement loginField = driver.findElement(By.name("username"));
        loginField.click();
        String login = "tomsmith";
        loginField.sendKeys(login);
        WebElement passField = driver.findElement(By.name("password"));
        passField.click();
        String pass = "SuperSecretPassword!";
        passField.sendKeys(pass);
        WebElement loginBtn = driver.findElement(By.tagName("button"));
        loginBtn.click();
        WebElement successMassage = driver.findElement(By.cssSelector("#flash"));
        String expectedMassage = "You logged into a secure area!";
        String actualMassage = successMassage.getText();
        Assert.assertTrue(actualMassage.contains(expectedMassage));
        WebElement logoutBtn = driver.findElement(By.partialLinkText("Logout"));
        logoutBtn.click();
    }
    @Test
    public void loginTest()
    {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.tagName("button")).click();
        String actualMsg = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg));
        driver.findElement(By.partialLinkText("Logout")).click();
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
}
