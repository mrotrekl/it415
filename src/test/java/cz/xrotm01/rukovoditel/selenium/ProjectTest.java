package cz.xrotm01.rukovoditel.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.UUID;

public class ProjectTest {

    private ChromeDriver driver;

    @Before
    public void init() {
        ChromeOptions cho = new ChromeOptions();

        boolean runOnTravis = true;
        if (runOnTravis) {
            cho.addArguments("headless");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        }
//        ChromeDriverService service = new ChromeDriverService()
        driver = new ChromeDriver(cho);
//        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.close();
    }


    // - TC: Project without name is not created
    @Test
    public void shouldNotCreateProjectwithoutName() {
        // given
        driver.get("http://digitalnizena.cz/rukovoditel/");

        // when
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("rukovoditel");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("vse456ru");
        WebElement loginButton = driver.findElement(By.className("btn-info"));
        loginButton.click();

        //najit rozklikavaci menu navbar-toggle collapsed
        WebElement navBarToggle = driver.findElement(By.className("navbar-toggle"));
        navBarToggle.click();

        //najit radek, kliknout na nej

        List<WebElement> menuRows = driver.findElements(By.cssSelector("ul.page-sidebar-menu>li"));
        for (WebElement e: menuRows) {
            if (e.getText().equals("Projects")) {
                e.click();
            }
        }

        // je treba odstranit problem s exception: element is not attached to the page document
        // pokud se pouzije vyse primo tento odkaz, problem nenastava driver.get("http://digitalnizena.cz/rukovoditel/index.php?module=items/items&path=21");

        WebElement projectButton = driver.findElement(By.cssSelector(".btn-primary"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(projectButton));
        projectButton.click();

        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        WebElement modalButton = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        wait2.until(ExpectedConditions.visibilityOf(modalButton));
        modalButton.click();

        // then
        WebElement errorMessageLabel = driver.findElement(By.cssSelector("#fields_158-error"));
        Assert.assertTrue(errorMessageLabel.getText().contains("This field is required!"));
    }

    @Test
    public void shouldCreateProjectwithName() {
        // given
        driver.get("http://digitalnizena.cz/rukovoditel/");

        // when
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("rukovoditel");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("vse456ru");
        WebElement loginButton = driver.findElement(By.className("btn-info"));
        loginButton.click();

        //najit rozklikavaci menu navbar-toggle collapsed
        WebElement navBarToggle = driver.findElement(By.className("navbar-toggle"));
        navBarToggle.click();

        //najit radek, kliknout na nej

        List<WebElement> menuRows = driver.findElements(By.cssSelector("ul.page-sidebar-menu>li"));
        for (WebElement e: menuRows) {
            if (e.getText().equals("Projects")) {
                e.click();
            }
        }

        // je treba odstranit problem s exception: element is not attached to the page document
        // pokud se pouzije vyse primo tento odkaz, problem nenastava driver.get("http://digitalnizena.cz/rukovoditel/index.php?module=items/items&path=21");

        WebElement projectButton = driver.findElement(By.cssSelector(".btn-primary"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(projectButton));
        projectButton.click();

        String uuid = UUID.randomUUID().toString();

        WebElement projectnameInput = driver.findElement(By.id("fields_158"));
        WebDriverWait wait2 = new WebDriverWait(driver, 5);
        wait2.until(ExpectedConditions.visibilityOf(projectnameInput));
        projectnameInput.sendKeys("xrotm01"+uuid);

        WebElement projectprioritySelect = driver.findElement(By.id("fields_156"));
        Select select = new Select(projectprioritySelect);
        select.selectByIndex(1);

        WebElement projectdateInput = driver.findElement(By.id("fields_159"));
        projectdateInput.click();

        WebElement activeDate = driver.findElement(By.cssSelector("td[class='active day']"));
        activeDate.click();

        WebElement modalButton = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton.click();

        // then
        Assert.assertTrue(driver.getTitle().contains("Tasks"));

        // when

        WebElement navBarToggle2 = driver.findElement(By.className("navbar-toggle"));
        navBarToggle2.click();
        List<WebElement> menuRows2 = driver.findElements(By.cssSelector("ul.page-sidebar-menu>li"));
        for (WebElement e: menuRows2) {
            if (e.getText().equals("Projects")) {
                e.click();
            }
        }

        // then

        WebElement projectTable = driver.findElement(By.cssSelector(".table-hover"));
        WebDriverWait wait3 = new WebDriverWait(driver, 5);
        wait3.until(ExpectedConditions.visibilityOf(projectTable));
        // kod je zrejme v poradku, presto vyjimka stale element reference: element is not attached to the page document
        String innerHTML = projectTable.getAttribute("innerHTML");
        Assert.assertTrue(innerHTML.contains(uuid));

    }

}
