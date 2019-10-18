package com.ctc.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SalesforceCreateEmployeePage {
    WebDriver driver = null;
    WebElement securityID = null;
    WebElement employeeName = null;
    WebElement userID = null;
    WebElement title = null;
    WebElement supervisor = null;
    WebElement positionID = null;
    WebElement DOB = null;
    WebElement mailDrop = null;
    WebElement empID = null;
    By newLink = By.linkText("New");
    By securityLabel = By.xpath("//*[text()[contains(.,'Security ID')]]");
    By userIDLabel = By.xpath("//*[text()[contains(.,'User ID')]]");
    By employeeLabel = By.xpath("//*[text()[contains(.,'EMP ID')]]");

    By save_button = By.xpath("//button[@title='Save']");



    public SalesforceCreateEmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    //New Employee click function
    public void newEmployeeClick() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(newLink));

        driver.findElement(newLink).click();
    }

    public void loadNewEmployeePage() {
        String parentWindowHandler = driver.getWindowHandle(); // Storing parent window
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler); // switch to popup window
        WebDriverWait wait = new WebDriverWait(driver, 5);
        System.out.println("subWindowHandler------->" + subWindowHandler);
    }

    public void setSecurityID(String securityid) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(securityLabel));

        WebElement label = driver.findElement(securityLabel);
        securityID = label.findElement(By.xpath("./following::input[1]"));
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(securityID));
        securityID.sendKeys(securityid);
    }

    public void setEmployeeName(String eName) {
        employeeName = securityID.findElement(By.xpath("./following::input[1]"));
        employeeName.sendKeys(eName);
    }

    public void setJobFunction(String job) {
        WebElement jobFunction = employeeName.findElement(By.xpath("./following::input[1]"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(jobFunction));
        jobFunction.sendKeys(job);
        jobFunction.sendKeys(Keys.DOWN);
        jobFunction.sendKeys("\n");
    }

    public void setUserID(String userid) {
        WebElement userlabel = driver.findElement(userIDLabel);
        userID = userlabel.findElement(By.xpath("./following::input[1]"));
        userID.sendKeys(userid);
    }

    public void setTitle(String inputTitle) {
        title = userID.findElement(By.xpath("./following::input[1]"));
        title.sendKeys(inputTitle);
    }

    public void setSupervisor(String superEmployee) {
        supervisor = title.findElement(By.xpath("./following::input[1]"));
        supervisor.sendKeys(superEmployee);
    }
    public void setpositionID(String positionid) {
        positionID = supervisor.findElement(By.xpath("./following::input[1]"));
        positionID.sendKeys(positionid);
    }
    public void setDOB(String dob) {
        DOB = positionID.findElement(By.xpath("./following::input[1]"));
        DOB.sendKeys(dob);
    }
    public void setMailDrop(String mail) {
        mailDrop = DOB.findElement(By.xpath("./following::input[1]"));
        mailDrop.sendKeys(mail);
    }

    public void setEmpID(String employeeID) {
        WebElement empLabel = driver.findElement(employeeLabel);
        empID = empLabel.findElement(By.xpath("./following::input[1]"));
        empID.sendKeys(employeeID);
    }

    //save Employee
    public void saveEmployee() {
        WebElement save = driver.findElement(save_button);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(save));
        save.click();
    }
}
