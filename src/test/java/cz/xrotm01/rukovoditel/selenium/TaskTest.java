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

        //najit radek, kliknout na nej

   /*     List<WebElement> menuRows = driver.findElements(By.cssSelector("ul.page-sidebar-menu>li"));
        for (WebElement e: menuRows) {
            if (e.getText().equals("Projects")) {
                e.click();
            }
        } */

        driver.get("http://digitalnizena.cz/rukovoditel/index.php?module=items/items&path=21");

        // je treba odstranit problem s exception: element is not attached to the page document
        // pokud se pouzije vyse primo tento odkaz, problem nenastava driver.get("http://digitalnizena.cz/rukovoditel/index.php?module=items/items&path=21");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".reset_search")));

        WebElement resetSearch = driver.findElement(By.cssSelector(".reset_search"));
        resetSearch.click();

        WebElement searchInput = driver.findElement(By.id("entity_items_listing66_21_search_keywords"));
        searchInput.sendKeys("xrotm01nemazat");

        WebElement projectButton2 = driver.findElement(By.className("btn-info"));
        projectButton2.click();

     /*   WebDriverWait wait2 = new WebDriverWait(driver, 5);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".item_heading_link")));

        WebElement projectLink = driver.findElement(By.cssSelector(".item_heading_link"));
       // projectLink = driver.findElement(By.cssSelector(".item_heading_link")); //prvni nacteni nefunguje
        projectLink.click();*/

        driver.get("http://digitalnizena.cz/rukovoditel/index.php?module=items/info&path=21-2792&redirect_to=subentity&gotopage[66]=1");
        // z duvodu not attached to the document exception

        WebDriverWait wait3 = new WebDriverWait(driver, 5);
        wait3.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary")));

        WebElement taskButton = driver.findElement(By.className("btn-primary"));
        taskButton.click();

        WebDriverWait wait4 = new WebDriverWait(driver, 5);
        wait4.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#fields_168")));

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

        WebDriverWait wait5 = new WebDriverWait(driver, 5);
        wait4.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".table-hover")));
        WebElement taskTable = driver.findElement(By.cssSelector(".table-hover"));
        String innerHTML = taskTable.getAttribute("innerHTML");
        Assert.assertTrue(innerHTML.contains(uuid));
    }

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

       /*     List<WebElement> menuRows = driver.findElements(By.cssSelector("ul.page-sidebar-menu>li"));
        for (WebElement e: menuRows) {
            if (e.getText().equals("Projects")) {
                e.click();
            }
        } */

        driver.get("http://digitalnizena.cz/rukovoditel/index.php?module=items/items&path=21");

        // je treba odstranit problem s exception: element is not attached to the page document
        // pokud se pouzije vyse primo tento odkaz, problem nenastava driver.get("http://digitalnizena.cz/rukovoditel/index.php?module=items/items&path=21");

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

  /*      for(int i = 0;i<7;i++) {

            WebElement taskButton = driver.findElement(By.className("btn-primary"));
            taskButton.click();
            WebDriverWait wait3 = new WebDriverWait(driver, 10);
            wait3.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#fields_168")));
            WebElement tasknameInput = driver.findElement(By.id("fields_168"));
            tasknameInput.sendKeys("xrotm01Task");
            WebElement taskstatusSelect = driver.findElement(By.id("fields_169"));
            Select select2 = new Select(taskstatusSelect);
            select2.selectByIndex(i);
            WebElement modalButton = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
            modalButton.click();

        }
*/
        // then
        List<WebElement> taskItems = driver.findElements(By.cssSelector(".table-hover tr"));
 //       Assert.assertEquals(4, taskItems.size());


        // when

   /*     WebDriverWait wait20 = new WebDriverWait(driver, 10);
        wait20.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa-trash-o")));
        WebElement resetFilter = driver.findElement(By.cssSelector(".fa-trash-o"));
        resetFilter.click();
*/

        WebElement editFilter = driver.findElement(By.cssSelector(".filters-preview-condition-include"));
        editFilter.click();

        WebDriverWait wait100 = new WebDriverWait(driver, 5);
        wait100.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search-choice-close")));
        List<WebElement> statusChoiceX = driver.findElements(By.cssSelector(".search-choice-close"));
        statusChoiceX.get(1).click();

        WebDriverWait wait21 = new WebDriverWait(driver, 5);
        wait21.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary-modal-action")));
        WebElement modalButton2 = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton2.click();

        // then

        List<WebElement> taskItems2 = driver.findElements(By.cssSelector(".table-hover tr"));
//        Assert.assertEquals(3, taskItems2.size());
        for (WebElement g: taskItems2)
            if (g.getAttribute("innerHTML").contains("Open"))
                Assert.assertEquals(4, 3);;


        // when

 /*       WebDriverWait wait22 = new WebDriverWait(driver, 10);
        wait22.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa fa-trash-o")));
        WebElement deleteFilterButton = driver.findElement(By.cssSelector(".fa fa-trash-o"));
        deleteFilterButton.click();
*/

        WebElement editFilter2 = driver.findElement(By.cssSelector(".filters-preview-condition-include"));
        editFilter2.click();

        WebDriverWait wait110 = new WebDriverWait(driver, 10);
        wait110.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search-choice-close")));
        List<WebElement> statusChoiceX2 = driver.findElements(By.cssSelector(".search-choice-close"));
        statusChoiceX2.get(1).click();
        statusChoiceX2.get(0).click();

        WebDriverWait wait31 = new WebDriverWait(driver, 5);
        wait31.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary-modal-action")));
        WebElement modalButton3 = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton3.click();

        // then
        List<WebElement> taskItems3 = driver.findElements(By.cssSelector(".table-hover tr"));
  //      Assert.assertEquals(7, taskItems3.size());


        // when
        WebDriverWait wait41 = new WebDriverWait(driver, 5);
        wait41.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select_all_items")));
        WebElement selectAllItemsButton = driver.findElement(By.cssSelector(".select_all_items"));
        selectAllItemsButton.click();

  //     WebElement withSelectedButton = driver.findElement(By.cssSelector(".btn btn-default dropdown-toggle"));
        WebElement taskdeleteSelect = driver.findElement(By.cssSelector(".btn btn-default dropdown-toggle"));
        Select select3 = new Select(taskdeleteSelect);
        select3.selectByIndex(1);

        // then
        List<WebElement> taskItems4 = driver.findElements(By.cssSelector(".table-hover tr"));
        Assert.assertEquals(0, taskItems4.size());

    }


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
