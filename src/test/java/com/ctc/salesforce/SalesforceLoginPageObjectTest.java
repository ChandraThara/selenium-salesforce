package com.ctc.salesforce;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.ctc.salesforce.pages.SalesforceCreateEmployeePage;
import com.ctc.salesforce.pages.SalesforceDeleteEmployee;
import com.ctc.salesforce.pages.SalesforceEditEmployee;
import com.ctc.salesforce.pages.SalesforceLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class SalesforceLoginPageObjectTest {
    WebDriver driver = null;
    WebElement rowActionsPlaceHolder = null;
    ExtentHtmlReporter htmlReporter = null;
    ExtentReports extent = null;
    ExtentTest test = null;

    @BeforeSuite
    public void setUp() {
        //Configuration for report generation
        // start reporters
        htmlReporter = new ExtentHtmlReporter("Reports.html");
        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeTest
    public void setUpTest() {
        System.setProperty("webdriver.chrome.driver","/Users/thara/Work/Selenium/chromedriver");

        ChromeOptions options=new ChromeOptions();
        //Passing chrome_Profile while initializing the ChromeDriver
        Map<String, Object> prefs=new HashMap<String,Object>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
        //1-Allow, 2-Block, 0-default
        options.setExperimentalOption("prefs",prefs);
        driver = new ChromeDriver(options);

    }

    @Test(priority = 0)
    public void salesforceLogin() {
        // creates a toggle for the given test, adds all log events under it
        SalesforceLoginPage salesforceLoginObj = new SalesforceLoginPage(driver);

        driver.get("https://login.salesforce.com");

        salesforceLoginObj.setUsername("*********");//Enter valid username
        salesforceLoginObj.setPassword("********");//Enter valid password
        salesforceLoginObj.logInButtonClick();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createEmployee() {

        SalesforceLoginPage salesforceLoginObj = new SalesforceLoginPage(driver);
        driver.get("https://login.salesforce.com");

        salesforceLoginObj.setUsername("*********");//Enter valid username
        salesforceLoginObj.setPassword("********");//Enter valid password
        salesforceLoginObj.logInButtonClick();

        //navigate to Employees home page
        driver.get("https://na114.lightning.force.com/lightning/o/SFDC_Employee__c/list?filterName=Recent");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        //load new Employee page
        SalesforceCreateEmployeePage newEmployeeObj = new SalesforceCreateEmployeePage(driver);
        newEmployeeObj.newEmployeeClick();

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        //navigate to New Employee page
        newEmployeeObj.loadNewEmployeePage();

        //create new employee
        newEmployeeObj.setSecurityID("123");
        newEmployeeObj.setEmployeeName("TRISH");
        newEmployeeObj.setJobFunction("Engineering");
        newEmployeeObj.setUserID("300");
        newEmployeeObj.setTitle("Ms");
        newEmployeeObj.setSupervisor("CHAND CHAN");
        newEmployeeObj.setpositionID("10");
        newEmployeeObj.setDOB("10/12/1990");
        newEmployeeObj.setEmpID("1213");

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //save employee click
        newEmployeeObj.saveEmployee();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createDummyEmployee() {

        SalesforceLoginPage salesforceLoginObj = new SalesforceLoginPage(driver);
        driver.get("https://login.salesforce.com");

        salesforceLoginObj.setUsername("*********");//Enter valid username
        salesforceLoginObj.setPassword("********");//Enter valid password
        salesforceLoginObj.logInButtonClick();

        //navigate to Employees home page
        driver.get("https://na114.lightning.force.com/lightning/o/SFDC_Employee__c/list?filterName=Recent");

        //load new Employee page
        SalesforceCreateEmployeePage newEmployeeObj = new SalesforceCreateEmployeePage(driver);
        newEmployeeObj.newEmployeeClick();

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        //navigate to New Employee page
        newEmployeeObj.loadNewEmployeePage();

        //create new employee
        newEmployeeObj.setSecurityID("123");
        newEmployeeObj.setEmployeeName("DUMMY");
        newEmployeeObj.setJobFunction("Engineering");
        newEmployeeObj.setUserID("300");
        newEmployeeObj.setTitle("Ms");
        newEmployeeObj.setSupervisor("CHAND CHAN");
        newEmployeeObj.setpositionID("10");
        newEmployeeObj.setDOB("10/12/1981");
        newEmployeeObj.setEmpID("1233");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newEmployeeObj.saveEmployee();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void editEmployeePage() {
        SalesforceLoginPage salesforceLoginObj = new SalesforceLoginPage(driver);
        driver.get("https://login.salesforce.com");

        salesforceLoginObj.setUsername("ctcstar@gmail.com");
        salesforceLoginObj.setPassword("admin123");
        salesforceLoginObj.logInButtonClick();

        //navigate to Employees home page
        driver.get("https://na114.lightning.force.com/lightning/o/SFDC_Employee__c/list?filterName=Recent");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("New")));

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        SalesforceEditEmployee editEmployeeObj = new SalesforceEditEmployee(driver);
        WebElement employeeTable = driver.findElement(By.className("slds-table"));

        List<WebElement> tableRows = employeeTable.findElements(By.tagName("tr"));

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        int index = -1;
        for (int i = 0; i < tableRows.size(); i++) {
            WebElement tableRow = tableRows.get(i);
            String rowtext = tableRow.getText();
            if (rowtext.contains("TRISH")) {
                index = i;
                break;
            }
        }

        if (index > -1) {
            rowActionsPlaceHolder = tableRows.get(index).findElement(By.className("forceVirtualActionMarker"));
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            WebElement li = rowActionsPlaceHolder.findElement(By.tagName("a"));
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
            li.click();

            wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Edit")));

            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
            driver.findElement(By.linkText("Edit")).click();//load edit employee page
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        //edit employee & save
        editEmployeeObj.loadEditEmployeePage();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        editEmployeeObj.setSecurityID("8645234");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        editEmployeeObj.saveEditEmployee();
    }

   // @Test(dependsOnMethods = "editEmployeePage")
    @Test
    public void deleteEmployee() {
        SalesforceLoginPage salesforceLoginObj = new SalesforceLoginPage(driver);
        driver.get("https://login.salesforce.com");

        salesforceLoginObj.setUsername("ctcstar@gmail.com");
        salesforceLoginObj.setPassword("admin123");
        salesforceLoginObj.logInButtonClick();

        //navigate to Employees home page
        driver.get("https://na114.lightning.force.com/lightning/o/SFDC_Employee__c/list?filterName=Recent");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("New")));

        SalesforceDeleteEmployee deleteEmployeeObj = new SalesforceDeleteEmployee(driver);
        WebElement employeeTable = driver.findElement(By.className("slds-table"));

        List<WebElement> tableRows = employeeTable.findElements(By.tagName("tr"));

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        int index = -1;
        for (int i = 0; i < tableRows.size(); i++) {
            WebElement tableRow = tableRows.get(i);
            String rowtext = tableRow.getText();
            if (rowtext.contains("TRISH")) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            test.log(Status.INFO,"Employee not found!.Test Completed!");
            Assert.assertFalse(true);

        }
        if (index > -1) {
            rowActionsPlaceHolder = tableRows.get(index).findElement(By.className("forceVirtualActionMarker"));
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            WebElement li = rowActionsPlaceHolder.findElement(By.tagName("a"));
            li.click();

            wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Delete")));
            driver.findElement(By.linkText("Delete")).click();//load edit employee page

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deleteEmployeeObj.deleteEmployee();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterTest
    public void tearDown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();
        extent.flush();
    }
}
