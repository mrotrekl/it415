package cz.vse.selenium;

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

/**
 * Unit test for simple App.
 */
public class AppTest {
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
        //WebElement dropDownToggle = driver.findElement(By.id("user_notifications_report"));
        WebElement dropDownToggle = driver.findElement(By.cssSelector("body > div.header.navbar.navbar-inverse.navbar-fixed-top.noprint > div > ul > li.dropdown.user"));
        dropDownToggle.click();

        //najit radek, kliknout na nej
/*      List<WebElement> menuInfoRows = driver.findElements(By.className("fa-sign-out"));
        WebElement oneRow = menuInfoRows.get(0);
        oneRow.click();
*/
        WebElement menuInfoRows = driver.findElement(By.className("fa-sign-out"));
        menuInfoRows.click();

        // then
        WebElement errorMessageDiv = driver.findElement(By.className("form-title"));
        Assert.assertTrue(errorMessageDiv.getText().contains("Login"));

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

  /*      List<WebElement> menuRows = driver.findElements(By.cssSelector("ul.page-sidebar-menu>li"));
        for (WebElement e: menuRows) {
            if (e.getText().equals("Projects")) {
                e.click();
            }
        }
*/
        driver.get("http://digitalnizena.cz/rukovoditel/index.php?module=items/items&path=21");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary")));

        WebElement projectButton = driver.findElement(By.cssSelector(".btn-primary"));
        projectButton.click();

        WebDriverWait wait2 = new WebDriverWait(driver, 5);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary-modal-action")));


        WebElement modalButton = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
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

  /*      List<WebElement> menuRows = driver.findElements(By.cssSelector("ul.page-sidebar-menu>li"));
        for (WebElement e: menuRows) {
            if (e.getText().equals("Projects")) {
                e.click();
            }
        }
*/
        driver.get("http://digitalnizena.cz/rukovoditel/index.php?module=items/items&path=21");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary")));

        WebElement projectButton = driver.findElement(By.cssSelector(".btn-primary"));
        projectButton.click();

        WebDriverWait wait2 = new WebDriverWait(driver, 5);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("fields_158")));


        WebElement projectnameInput = driver.findElement(By.id("fields_158"));
        projectnameInput.sendKeys("xrotm01");

        WebElement projectprioritySelect = driver.findElement(By.id("fields_156"));
        Select select = new Select(projectprioritySelect);
        select.selectByIndex(1);

        WebElement projectdateInput = driver.findElement(By.id("fields_159"));
        projectdateInput.click();

        WebElement activeDate = driver.findElement(By.cssSelector("td[class='active day']")); //proc negungovalo classname? s mezerou
        activeDate.click();

        WebElement modalButton = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        modalButton.click();

        // then
        Assert.assertTrue(driver.getTitle().contains("Tasks"));
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
 - TC: (Precondition - there exists project yourname already in the system.) Create new 7 tasks with different statuses New, Open, Waiting, Done, Closed, Paid, Canceled. Verify that using default filter (New, Open, Waiting) only 3 tasks will be shown. Change applied filter in Filter info dialog to only contain (New, Waiting) ...there are more ways how to do it (you can click small x on Open "label" to delete it, or you can deal with writing into "suggestion box"). Verify only New and Waiting tasks are displayed. Now remove all filters and verify all created tasks are displayed. Delete all tasks using Select all and batch delete.
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

/*
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

        WebElement taskButton12 = driver.findElement(By.className("btn-primary"));
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
    }


    @Test
    public void google1_should_pass() {
        driver.get("https://www.google.com/");
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("travis");
        searchInput.sendKeys(Keys.ENTER);
        Assert.assertTrue(driver.getTitle().startsWith("travis - "));
        //driver.quit();
    }

    @Test
    public void alzaTest() throws InterruptedException {
        driver.get("https://www.alza.cz/");
        WebElement searchInput = driver.findElement(By.cssSelector("#edtSearch"));
        searchInput.sendKeys("ubiquiti unifi");

        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.presenceOfElementLocated( By.cssSelector("#ui-id-1>li.t7") ));

        List<WebElement> searchItems = driver.findElements(By.cssSelector("#ui-id-1>li.t3"));
        Assert.assertEquals(3, searchItems.size());
        driver.quit();
    }

    @Test
    public void google2_should_fail() {
        driver.get("https://www.google.com/");
       // WebElement searchInputNotExisting = driver.findElement(By.name("kdsfkladsjfas"));
        driver.quit();
    }

    @Test
    public void google3_should_fail() {
        driver.get("https://www.google.com/");
        Assert.assertEquals("one", "one");
        driver.quit();
    }

    @Test
    public void shouldNotLoginUsingInvalidPassword() {
        // given
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // when
        WebElement usernameInput = driver.findElement(By.id("txtUsername"));
        usernameInput.sendKeys("admin");
        WebElement passwordInput = driver.findElement(By.id("txtPassword"));
        passwordInput.sendKeys("invalidPassssssssword");
        WebElement loginButton = driver.findElement(By.id("btnLogin"));
        loginButton.click();

        // then
        WebElement errorMessageSpan = driver.findElement(By.id("spanMessage"));
        Assert.assertEquals("Invalid credentials", errorMessageSpan.getText());

        // validation error exists
        // url changed to https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials
        // there is no menu
    }


    @Test
    public void shouldLoginUsingValidCredentials0() {
        // given
        //driver.get("http://demo.churchcrm.io/master/");
        driver.get("http://digitalnizena.cz/church/");

        // when
        WebElement usernameInput = driver.findElement(By.id("UserBox"));
        usernameInput.sendKeys("church");
        WebElement passwordInput = driver.findElement(By.id("PasswordBox"));
        passwordInput.sendKeys("church12345");
        WebElement loginButton = driver.findElement(By.className("btn-primary"));
        //loginButton.click();
    }

    public void shouldCreateNewUser() throws InterruptedException {
        // Given
        shouldLoginUsingValidCredentials();

        // When
        driver.get("http://digitalnizena.cz/church/PersonEditor.php");

        WebElement genderSelectElement = driver.findElement(By.name("Gender"));
        Select genderSelect = new Select(genderSelectElement);
        genderSelect.selectByVisibleText("Male");

        WebElement firstNameInput = driver.findElement(By.id("FirstName"));
        firstNameInput.sendKeys("John");
        WebElement lastNameInput = driver.findElement(By.id("LastName"));
        String uuid = UUID.randomUUID().toString();
        lastNameInput.sendKeys("Doe " + uuid);
        WebElement emailInput = driver.findElement(By.name("Email"));
        emailInput.sendKeys("john.doe@gmail.com");

        WebElement classificationSelectElement = driver.findElement(By.name("Classification"));
        Select classificationSelect = new Select(classificationSelectElement);
        classificationSelect.selectByIndex(4);

        WebElement personSaveButton = driver.findElement(By.id("PersonSaveButton"));
        personSaveButton.click();

        // Then
        driver.get("http://digitalnizena.cz/church/v2/people");

        WebElement searchInput = driver.findElement(By.cssSelector("#members_filter input"));
        searchInput.sendKeys(uuid);
        Thread.sleep(500);

        // to verify if record is shown in table grid, we first filter the whole table to get exactly one data row
        // that row should contain previously generated UUID value (in last name
        // UKOL...opravit, doplnit tak, aby se provedla verifikace ze kontakt, ktery jsme vytvorili opravdu existuje
        //    (jde vyhledat a zobrazi se v tabulce)
        //    doporucuji radek tabulky s danou osobou projit (traverzovat), nebo jinym zpusobem v nem najit retezec UUID, ktery jednoznacne identifikuje pridanou osobu
        List<WebElement> elements = driver.findElements(By.cssSelector("table#members tr"));
        Assert.assertEquals(2, elements.size());

        // data row is at index 0, header row is at index 1  (because in ChurchCRM html code there is tbody before thead)
        WebElement personTableRow = elements.get(0);


        // option1
        Assert.assertTrue(personTableRow.getText().contains(uuid));

        // option2 - traverse all cells in table grid
        List<WebElement> cells = personTableRow.findElements(By.tagName("td"));
        final int EXPECTED_COLUMNS = 9;
        Assert.assertEquals(EXPECTED_COLUMNS, cells.size());
        for (int i = 0; i < cells.size(); i++) {
            WebElement cell = cells.get(i);
            if (cell.getText().contains(uuid)) {
                //
            }

            System.out.println(cells.get(i).getText());
        }
    }


    @Test
    public void given_userIsLoggedIn_when_userAddsNewDeposit_then_depositRecordIsShownInDepositTableGrid() throws InterruptedException {
        // GIVEN user is logged in

        shouldLoginUsingValidCredentials();

        // WHEN user adds deposit comment

        driver.get("http://digitalnizena.cz/church/FindDepositSlip.php");

        WebElement depositCommentInput = driver.findElement(By.cssSelector("#depositComment"));
        String uuid = UUID.randomUUID().toString();
        String depositComment = "deposit-PavelG-" + uuid;
        depositCommentInput.sendKeys(depositComment);

        WebElement depositDateInput = driver.findElement(By.cssSelector("#depositDate"));
        depositDateInput.click();
        depositDateInput.clear();
        depositDateInput.sendKeys("2018-10-30");

        WebElement addDepositButton = driver.findElement(By.cssSelector("#addNewDeposit"));
        addDepositButton.click();

        // THEN newly added deposit should be shown in deposits table grid

        // option1 - wait exactly 2 seconds, blocks the thread ....not recommended
        // Thread.sleep(2000);

        // option2 - use custom "expected condition" of WebDriver framework
        WebDriverWait wait = new WebDriverWait(driver, 2);     // timeout after 2 seconds
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                // each time, we try to get the very first row from table grid and check, if contains the last record

                List<WebElement> depositRows = driver.findElements(By.cssSelector("#depositsTable_wrapper #depositsTable tbody tr"));
                WebElement firstRow = depositRows.get(0);
                String innerHTML = firstRow.getAttribute("innerHTML");

                if (innerHTML.contains(uuid)) {
                    Assert.assertTrue(innerHTML.contains("10-30-18"));    // beware, different date format in table grid vs. input field
                    Assert.assertTrue(innerHTML.contains(depositComment));
                    return true;     // expected condition is met
                } else {
                    return false;    // selenium webdriver will continue polling the DOM each 500ms and check the expected condition by calling method apply(webDriver) again
                }
            }
        });
    }

    public void deleteDeposits() throws InterruptedException {
        shouldLoginUsingValidCredentials();

        driver.get("http://digitalnizena.cz/church/FindDepositSlip.php");

        Thread.sleep(1000);

        List<WebElement> depositRows = driver.findElements(By.cssSelector("#depositsTable tbody tr"));

        for (WebElement row : depositRows) {
            row.click();
        }

//
        WebElement deleteButton = driver.findElement(By.cssSelector("#deleteSelectedRows"));
        deleteButton.click();
//
//        //TODO compare this WebElement confirmDeleteButton = driver.findElement(By.cssSelector(".modal-dialog .btn-primary"));
        WebElement confirmDeleteButton = driver.findElement(By.cssSelector(".modal-content > .modal-footer .btn-primary"));
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOf(confirmDeleteButton));
        confirmDeleteButton.click();

//        // actually the application behaves incorrect => when delete all rows, Delete button should be disabled
//        // we have our test correct, so it good that test fails!
        Assert.assertFalse(deleteButton.isEnabled());
    }

    public void loadingExample() {
        driver.get("http://digitalnizena.cz/priklad/loading1.html");

        WebElement button = driver.findElement(By.cssSelector("#my-button"));

        WebDriverWait wait = new WebDriverWait(driver, 12);
        wait.until(ExpectedConditions.visibilityOf(button));

        // here in code, we are 100% sure, that button is visible
    }


}
