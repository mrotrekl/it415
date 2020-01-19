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

public class LoginTest {

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

    @Test
    public void shouldLoginUsingValidCredentials() {
        // given
        driver.get("http://digitalnizena.cz/rukovoditel/");

        // when
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("rukovoditel");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("vse456ru");
        WebElement loginButton = driver.findElement(By.className("btn-info"));
        loginButton.click();

        // then
        Assert.assertTrue(driver.getTitle().contains("Dashboard"));
    }



    @Test
    public void shouldNotLoginUsingInvalidCredentials() {
        // given
        driver.get("http://digitalnizena.cz/rukovoditel/");

        // when
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("admin");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("invalidPassssssssword");
        WebElement loginButton = driver.findElement(By.className("btn-info"));
        loginButton.click();

        // then
        WebElement errorMessageDiv = driver.findElement(By.className("alert-danger"));
        Assert.assertTrue(errorMessageDiv.getText().contains("No match"));
    }

    @Test
    public void shouldLogOff() {
        // given
        driver.get("http://digitalnizena.cz/rukovoditel/");

        // when
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("rukovoditel");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("vse456ru");
        WebElement loginButton = driver.findElement(By.className("btn-info"));
        loginButton.click();

        //najit rozklikavaci menu
        WebElement dropDownToggle = driver.findElement(By.cssSelector("body > div.header.navbar.navbar-inverse.navbar-fixed-top.noprint > div > ul > li.dropdown.user"));
        dropDownToggle.click();

        //najit radek, kliknout na nej
        WebElement menuInfoRows = driver.findElement(By.className("fa-sign-out"));
        menuInfoRows.click();

        // then
        WebElement errorMessageDiv = driver.findElement(By.className("form-title"));
        Assert.assertTrue(errorMessageDiv.getText().contains("Login"));

    }


}
