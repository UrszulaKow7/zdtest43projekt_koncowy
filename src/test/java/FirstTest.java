import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class FirstTest {


    WebDriver driver;
    WebDriverWait wait;


    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("wykonuje sie tutaj! przed metoda testowa");
        wait = new WebDriverWait(driver,10);
    }

    @Test //kroki testowe - po prostu test do wykonania
    public void firstTest() {
        driver.get("https://dev.to"); //przejdź na stronę dev.to
        WebElement sideBarVideo = driver.findElement(By.xpath("/html/body/div[9]/div/div/div[1]/aside/nav[1]/div/a[3]")); //znajdz element week
        highlightElement(sideBarVideo);
        //sideBarVideo.click(); //klikamy w weekBtn
    }

    @Test
    public void openFirstVideoPage(){
        driver.get("https://dev.to");
        WebElement sideBarVideo = driver.findElement(By.partialLinkText("Videos"));
        highlightElement(sideBarVideo);
        sideBarVideo.click();
        //przechodzimy na strone z video

        wait.until(ExpectedConditions.urlToBe("https://dev.to/videos"));
        WebElement firstVideo = driver.findElement(By.className("video-image"));
        highlightElement(firstVideo);
        firstVideo.click();
    }


    @Test
    public void highlightFirstVideo(){
        driver.get("https://dev.to/videos");
        WebElement firstVideo = driver.findElement(By.className("video-image"));
        highlightElement(firstVideo);
        firstVideo.click();

    }
    @Test
    public void selectFirstPodcast(){
        driver.get("https://dev.to");
        WebElement podcasts = driver.findElement(By.partialLinkText("Podcast"));
        podcasts.click();
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));

        WebElement firstPodcasts = driver.findElement(By.cssSelector(".content > h3:first-child"));
        String podcastsTitleFromList = firstPodcasts.getText();

        String firstPodcastsFromListLink = driver.findElement(By.cssSelector("#substories > a:first-child")).getAttribute("href");


        firstPodcasts.click();
        wait.until(ExpectedConditions.urlToBe(firstPodcastsFromListLink));
        WebElement podcastsTitle = driver.findElement(By.cssSelector(".title > h1:nth-child(2)"));


        String podcastTitleText = podcastsTitle.getText();
        assertTrue(podcastsTitleFromList.contains(podcastTitleText));

        WebElement record = driver.findElement(By.className("record"));
        record.click();

        WebElement initializing = driver.findElement(By.className("status-message"));
        wait.until(ExpectedConditions.invisibilityOf(initializing));
        WebElement recordWrapper = driver.findElement(By.className("record-wrapper"));
        String classAttribute = recordWrapper.getAttribute("class");
        Boolean isPodcastPlayed = classAttribute.contains("playing");

        assertTrue(isPodcastPlayed);

    }


}



