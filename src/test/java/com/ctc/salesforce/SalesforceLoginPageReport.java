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


public class SalesforceLoginPageReport {
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
        test = extent.createTest("Login page", "TC1 : TC to test Sales force login Functionality.");
        test.log(Status.INFO,"Starting TC 1:");

        SalesforceLoginPage salesforceLoginObj = new SalesforceLoginPage(driver);

        driver.get("https://login.salesforce.com");

        test.pass("Navigated to salesforce.com!");

        salesforceLoginObj.setUsername("*********");//Enter valid username
        test.pass("Entered valid username");
        salesforceLoginObj.setPassword("********");//Enter valid password
        test.pass("Entered valid password");
        salesforceLoginObj.logInButtonClick();
        test.pass("Log in successfull!");

        test.log(Status.INFO,"Test Completed Successfully!");
        test = null;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createEmployee() {
        test = extent.createTest("Create Employee", "TC2 : TC to test Employee Manager's Create Employee Functionality.");
        test.log(Status.INFO,"Starting TC 2:");

        SalesforceLoginPage salesforceLoginObj = new SalesforceLoginPage(driver);
        driver.get("https://login.salesforce.com");

        salesforceLoginObj.setUsername("*********");//Enter valid username
        test.pass("Entered valid username");
        salesforceLoginObj.setPassword("********");//Enter valid password
        test.pass("Entered valid password");
        salesforceLoginObj.logInButtonClick();

        test.pass("Log in successfull!");

        //navigate to Employees home page
        driver.get("https://na114.lightning.force.com/lightning/o/SFDC_Employee__c/list?filterName=Recent");

        test.pass("Employee Manager App loaded!");

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        //load new Employee page
        SalesforceCreateEmployeePage newEmployeeObj = new SalesforceCreateEmployeePage(driver);
        newEmployeeObj.newEmployeeClick();
        test.pass("Clicked on New Tab!");


        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        //navigate to New Employee page
        newEmployeeObj.loadNewEmployeePage();
        test.pass("New Employee page loaded!");

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
        test.pass("Filled in employee details!");

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //save employee click
        newEmployeeObj.saveEmployee();

        test.pass("New Employee created Successfully!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.log(Status.INFO,"Test Completed Successfully!");
        test = null;
    }

    @Test
    public void createDummyEmployee() {
        test = extent.createTest("Create Employee", "TC3 : TC to test Employee Manager's Create Employee Functionality.");
        test.log(Status.INFO,"Starting TC 3:");

        SalesforceLoginPage salesforceLoginObj = new SalesforceLoginPage(driver);
        driver.get("https://login.salesforce.com");

        salesforceLoginObj.setUsername("*********");//Enter valid username
        test.pass("Entered valid username");
        salesforceLoginObj.setPassword("********");//Enter valid password
        test.pass("Entered valid password");
        salesforceLoginObj.logInButtonClick();

        test.pass("Log in successfull!");

        //navigate to Employees home page
        driver.get("https://na114.lightning.force.com/lightning/o/SFDC_Employee__c/list?filterName=Recent");

        test.pass("Employee Manager App loaded!");

        //load new Employee page
        SalesforceCreateEmployeePage newEmployeeObj = new SalesforceCreateEmployeePage(driver);
        newEmployeeObj.newEmployeeClick();

        test.pass("Clicked on New Tab!");


        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        //navigate to New Employee page
        newEmployeeObj.loadNewEmployeePage();

        test.pass("New Employee page loaded!");


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
        test.pass("Filled in employee details!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.log(Status.INFO,"Test Completed Successfully!");
        test = null;
    }

    @Test
    public void editEmployeePage() {
        test = extent.createTest("Edit Employee", "TC4 : TC to test Employee Manager's Edit Employee Functionality.");
        test.log(Status.INFO,"Starting TC 4:");

        SalesforceLoginPage salesforceLoginObj = new SalesforceLoginPage(driver);
        driver.get("https://login.salesforce.com");

        salesforceLoginObj.setUsername("ctcstar@gmail.com");
        salesforceLoginObj.setPassword("admin123");
        salesforceLoginObj.logInButtonClick();

        test.pass("Log in successfull!");

        //navigate to Employees home page
        driver.get("https://na114.lightning.force.com/lightning/o/SFDC_Employee__c/list?filterName=Recent");

        test.pass("Employee Manager App loaded!");

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
            test.pass("Clicked on Edit menu!");

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        //edit employee & save
        editEmployeeObj.loadEditEmployeePage();
        test.pass("Edit Employee loaded Successfully!");

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        editEmployeeObj.setSecurityID("8645234");
        test.pass("Updated employee field!");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

        editEmployeeObj.saveEditEmployee();
        test.pass("Updated Employee details.");

        test.log(Status.INFO,"Test Completed Successfully!");
        test = null;
    }

    // @Test(dependsOnMethods = "editEmployeePage")
    @Test
    public void deleteEmployee() {
        test = extent.createTest("Delete Employee", "TC5 : TC to test Employee Manager's Delete Employee Functionality.");
        test.log(Status.INFO,"Starting TC 5:");

        SalesforceLoginPage salesforceLoginObj = new SalesforceLoginPage(driver);
        driver.get("https://login.salesforce.com");

        salesforceLoginObj.setUsername("ctcstar@gmail.com");
        salesforceLoginObj.setPassword("admin123");
        salesforceLoginObj.logInButtonClick();

        test.pass("Log in successfull!");

        //navigate to Employees home page
        driver.get("https://na114.lightning.force.com/lightning/o/SFDC_Employee__c/list?filterName=Recent");

        test.pass("Employee Manager App loaded!");

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
            test.pass("Clicked on Delete menu!");

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
            test.pass("Loaded Delete Employee confirmation popup!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deleteEmployeeObj.deleteEmployee();
            test.pass("Employee deleted Successfully!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.log(Status.INFO,"Test Completed Successfully!");
            test = null;
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
