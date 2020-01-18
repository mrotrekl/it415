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

public class TaskTest {

    private ChromeDriver driver;

    @Before
    public void init() {
        ChromeOptions cho = new ChromeOptions();

        boolean runOnTravis = false;
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
//        driver.close();
    }


    @Test
    public void shouldCreateNewTask() {
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

        //najit radek, kliknout na nej fa fa-reorder
/*        WebElement projectsItem = driver.findElement(By.className("fa-reorder"));
        projectsItem.click();
*/

        List<WebElement> menuRows = driver.findElements(By.className("page-sidebar-menu"));
        WebElement secondRow = menuRows.get(0);
        secondRow.click();

        driver.get("http://digitalnizena.cz/rukovoditel/index.php?module=items/items&path=21");


        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".reset_search")));

        WebElement resetSearch = driver.findElement(By.cssSelector(".reset_search"));
        resetSearch.click();

        WebElement searchInput = driver.findElement(By.id("entity_items_listing66_21_search_keywords"));
        searchInput.sendKeys("xrotm01nemazat");

        WebElement projectButton = driver.findElement(By.className("btn-info"));
        projectButton.click();

        WebDriverWait wait2 = new WebDriverWait(driver, 5);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".item_heading_link")));

        WebElement projectLink = driver.findElement(By.cssSelector(".item_heading_link"));
        projectLink = driver.findElement(By.cssSelector(".item_heading_link")); //prvni nacteni nefunguje
        projectLink.click();

        WebElement taskButton = driver.findElement(By.className("btn-primary"));
        taskButton.click();


        WebDriverWait wait3 = new WebDriverWait(driver, 5);
        wait3.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#fields_168")));


        String uuid = UUID.randomUUID().toString();
        WebElement tasknameInput = driver.findElement(By.id("fields_168"));
        tasknameInput.sendKeys("xrotm01"+uuid+"Task");

        WebElement tasktypeSelect = driver.findElement(By.id("fields_167"));
        Select select = new Select(tasktypeSelect);
        select.selectByIndex(0);


        WebElement taskstatusSelect = driver.findElement(By.id("fields_167"));
        Select select2 = new Select(taskstatusSelect);
        select2.selectByIndex(0);

        WebElement taskprioSelect = driver.findElement(By.id("fields_170"));
        Select select3 = new Select(taskprioSelect);
        select3.selectByIndex(2);

        //type Task, name, status New, prio Medium and some description

        WebElement descriptionIFrame = driver.findElement(By.cssSelector(".cke_wysiwyg_frame"));
        driver.switchTo().frame(descriptionIFrame);
        WebElement descriptionBody = driver.findElement(By.cssSelector(".cke_contents_ltr"));
        descriptionBody.sendKeys("mydescription");
        driver.switchTo().defaultContent();


        WebElement modalButton = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton.click();

        // then

        WebDriverWait wait4 = new WebDriverWait(driver, 5);
        wait4.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".table-hover")));
        WebElement taskTable = driver.findElement(By.cssSelector(".table-hover"));
        String innerHTML = taskTable.getAttribute("innerHTML");
        Assert.assertTrue(innerHTML.contains(uuid));
    }
/*
 - TC: Project with status New, priority High and filled start date as today is created. Verify that there is new row in project table. Delete project after test.
    Test Suite - Tasks
 - TC: shouldCreateNewTask (Precondition - there exists project yourname already in the system.) New Task will be created with type Task, name, status New, prio Medium and some description. Verify task attributes (Type Task, description, name, priority, status) on task info page (icon i). Delete that task.
 - TC: (Precondition - there exists project yourname already in the system.)
    Create new 7 tasks with different statuses New, Open, Waiting, Done, Closed, Paid, Canceled.
    Verify that using default filter (New, Open, Waiting) only 3 tasks will be shown.
    Change applied filter in Filter info dialog to only contain (New, Waiting)
    ...there are more ways how to do it (you can click small x on Open "label" to delete it,
    or you can deal with writing into "suggestion box").
    Verify only New and Waiting tasks are displayed. Now remove all filters and verify
     all created tasks are displayed. Delete all tasks using Select all and batch delete.
*/

    @Test
    public void shouldCreateNew7Tasks() {
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

        //najit radek, kliknout na nej fa fa-reorder
/*        WebElement projectsItem = driver.findElement(By.className("fa-reorder"));
        projectsItem.click();
*/

        List<WebElement> menuRows = driver.findElements(By.className("page-sidebar-menu"));
        WebElement secondRow = menuRows.get(0);
        secondRow.click();

        driver.get("http://digitalnizena.cz/rukovoditel/index.php?module=items/items&path=21");


//        WebElement resetSearch = driver.findElement(By.className("reset_search")); //


        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".reset_search")));

        WebElement resetSearch = driver.findElement(By.cssSelector(".reset_search"));
        resetSearch.click();

        WebElement searchInput = driver.findElement(By.id("entity_items_listing66_21_search_keywords"));
        searchInput.sendKeys("xrotm01");

        WebElement projectButton = driver.findElement(By.className("btn-info"));
        projectButton.click();

        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".item_heading_link")));

        WebElement projectLink = driver.findElement(By.cssSelector(".item_heading_link"));
        projectLink.click();


        WebElement taskButton = driver.findElement(By.className("btn-primary"));
        taskButton.click();
        WebDriverWait wait3 = new WebDriverWait(driver, 10);
        wait3.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#fields_168")));
        WebElement tasknameInput = driver.findElement(By.id("fields_168"));
        tasknameInput.sendKeys("xrotm01Task");
        WebElement taskstatusSelect = driver.findElement(By.id("fields_169"));
        Select select2 = new Select(taskstatusSelect);
        select2.selectByIndex(0);
        WebElement modalButton = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton.click();

/*        WebElement taskButton12 = driver.findElement(By.className("btn-primary"));
        taskButton12.click();
        WebDriverWait wait12 = new WebDriverWait(driver, 10);
        wait12.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#fields_168")));
        WebElement tasknameInput12 = driver.findElement(By.id("fields_168"));
        tasknameInput12.sendKeys("xrotm01Task");
        WebElement taskstatusSelect12 = driver.findElement(By.id("fields_169"));
        Select select12 = new Select(taskstatusSelect12);
        select12.selectByIndex(1);
        WebElement modalButton12 = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton12.click();

        WebElement taskButton13 = driver.findElement(By.className("btn-primary"));
        taskButton13.click();
        WebDriverWait wait13 = new WebDriverWait(driver, 10);
        wait13.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#fields_168")));
        WebElement tasknameInput13 = driver.findElement(By.id("fields_168"));
        tasknameInput13.sendKeys("xrotm01Task");
        WebElement taskstatusSelect13 = driver.findElement(By.id("fields_169"));
        Select select13 = new Select(taskstatusSelect13);
        select13.selectByIndex(2);
        WebElement modalButton13 = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton13.click();

        WebElement taskButton14 = driver.findElement(By.className("btn-primary"));
        taskButton14.click();
        WebDriverWait wait14 = new WebDriverWait(driver, 10);
        wait14.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#fields_168")));
        WebElement tasknameInput14 = driver.findElement(By.id("fields_168"));
        tasknameInput14.sendKeys("xrotm01Task");
        WebElement taskstatusSelect14 = driver.findElement(By.id("fields_169"));
        Select select14 = new Select(taskstatusSelect14);
        select14.selectByIndex(3);
        WebElement modalButton14 = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton14.click();

        WebElement taskButton15 = driver.findElement(By.className("btn-primary"));
        taskButton15.click();
        WebDriverWait wait15 = new WebDriverWait(driver, 10);
        wait15.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#fields_168")));
        WebElement tasknameInput15 = driver.findElement(By.id("fields_168"));
        tasknameInput15.sendKeys("xrotm01Task");
        WebElement taskstatusSelect15 = driver.findElement(By.id("fields_169"));
        Select select15 = new Select(taskstatusSelect15);
        select15.selectByIndex(4);
        WebElement modalButton15 = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton15.click();

        WebElement taskButton16 = driver.findElement(By.className("btn-primary"));
        taskButton16.click();
        WebDriverWait wait16 = new WebDriverWait(driver, 10);
        wait16.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#fields_168")));
        WebElement tasknameInput16 = driver.findElement(By.id("fields_168"));
        tasknameInput16.sendKeys("xrotm01Task");
        WebElement taskstatusSelect16 = driver.findElement(By.id("fields_169"));
        Select select16 = new Select(taskstatusSelect16);
        select16.selectByIndex(5);
        WebElement modalButton16 = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton16.click();

        WebElement taskButton17 = driver.findElement(By.className("btn-primary"));
        taskButton17.click();
        WebDriverWait wait17 = new WebDriverWait(driver, 10);
        wait17.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#fields_168")));
        WebElement tasknameInput17 = driver.findElement(By.id("fields_168"));
        tasknameInput17.sendKeys("xrotm01Task");
        WebElement taskstatusSelect17 = driver.findElement(By.id("fields_169"));
        Select select17 = new Select(taskstatusSelect17);
        select17.selectByIndex(6);
        WebElement modalButton17 = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton17.click();
*/





        // Then

/*        WebElement firstRow = depositRows.get(0);
        String innerHTML = firstRow.getAttribute("innerHTML");

        if (innerHTML.contains(uuid)) {
            Assert.assertTrue(innerHTML.contains("10-30-18"));    // beware, different date format in table grid vs. input field
            Assert.assertTrue(innerHTML.contains(depositComment));
            return true;     // expected condition is met
        } else {
            return false;    // selenium webdriver will continue polling the DOM each 500ms and check the expected condition by calling method apply(webDriver) again
        }
*/


   /*     if (innerHTML.contains(uuid)) {
            Assert.assertTrue(innerHTML.contains("10-30-18"));    // beware, different date format in table grid vs. input field
            Assert.assertTrue(innerHTML.contains(depositComment));
            return true;     // expected condition is met
        } else {
            return false;    // selenium webdriver will continue polling the DOM each 500ms and check the expected condition by calling method apply(webDriver) again
        } */

        // Then

/*        WebElement firstRow = depositRows.get(0);
        String innerHTML = firstRow.getAttribute("innerHTML");

        if (innerHTML.contains(uuid)) {
            Assert.assertTrue(innerHTML.contains("10-30-18"));    // beware, different date format in table grid vs. input field
            Assert.assertTrue(innerHTML.contains(depositComment));
            return true;     // expected condition is met
        } else {
            return false;    // selenium webdriver will continue polling the DOM each 500ms and check the expected condition by calling method apply(webDriver) again
        }
*/



//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("$('#btn-primary').click()");

//        class="btn btn-primary btn-primary-modal-action"


/*       for (WebElement row : menuRows) {
            row.click();
        }

*/
        // Then

/*        WebElement firstRow = depositRows.get(0);
        String innerHTML = firstRow.getAttribute("innerHTML");

        if (innerHTML.contains(uuid)) {
            Assert.assertTrue(innerHTML.contains("10-30-18"));    // beware, different date format in table grid vs. input field
            Assert.assertTrue(innerHTML.contains(depositComment));
            return true;     // expected condition is met
        } else {
            return false;    // selenium webdriver will continue polling the DOM each 500ms and check the expected condition by calling method apply(webDriver) again
        }
*/



//        WebElement errorMessageSpan = driver.findElement(By.id("spanMessage"));
        //   Assert.assertEquals("Invalid credentials", errorMessageSpan.getText());

        // validation error exists
        // url changed to https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials
        // there is no menu


        //       Assert.assertEquals("No match", errorMessageDiv.getText());

        // validation error exists
        // url changed to https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials
        // there is no menu





        // Then

/*        WebElement firstRow = depositRows.get(0);
        String innerHTML = firstRow.getAttribute("innerHTML");

        if (innerHTML.contains(uuid)) {
            Assert.assertTrue(innerHTML.contains("10-30-18"));    // beware, different date format in table grid vs. input field
            Assert.assertTrue(innerHTML.contains(depositComment));
            return true;     // expected condition is met
        } else {
            return false;    // selenium webdriver will continue polling the DOM each 500ms and check the expected condition by calling method apply(webDriver) again
        }
*/
//        Assert.assertTrue(driver.getTitle().contains("Tasks"));

        //       List<WebElement> depositRows = driver.findElements(By.cssSelector("#depositsTable_wrapper #depositsTable tbody tr"));
        //       WebElement firstRow = depositRows.get(0);

    }








}
