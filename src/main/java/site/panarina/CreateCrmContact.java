package site.panarina;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CreateCrmContact {

    private static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

        loginCrm();

        Actions actions = new Actions(driver);
        WebElement projectMenuElement = driver.findElement(By.xpath("//a/span[text()='Контрагенты']"));
        actions.moveToElement(projectMenuElement).perform();

        driver.findElement(By.xpath("//*[text()=\"Контактные лица\"]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()=\"Создать контактное лицо\"]")));
        driver.findElement(By.xpath("//*[text()=\"Создать контактное лицо\"]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'crm_contact_lastName')]")));
        driver.findElement(By.xpath("//input[contains(@id,'crm_contact_lastName')]")).sendKeys("Ivanov");
        driver.findElement(By.xpath("//input[contains(@id,'crm_contact_firstName')]")).sendKeys("Ivan");
        driver.findElement(By.xpath("//input[contains(@id,'crm_contact_jobTitle')]")).sendKeys("software tester");

        driver.findElement(By.xpath("//span[@class=\"select2-chosen\"]")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//div[@class=\"select2-result-label\"])[2]")));
        driver.findElement(By.xpath("(.//div[@class=\"select2-result-label\"])[2]")).click();

        driver.findElement(By.xpath("//button[contains(@class,\"main-group\")][contains(.,'Сохранить')]")).click();

        driver.quit();
    }

    public static void loginCrm() {
        driver.get("https://crm.geekbrains.space/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.xpath("//button")).click();
    }
}