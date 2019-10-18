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

public class SalesforceEditEmployee {
    WebDriver driver = null;
    WebElement securityID = null;

    By securityLabel = By.xpath("//*[text()[contains(.,'Security ID')]]");
    By editLabel = By.xpath("//*[text()[contains(.,'Edit')]]");
    By save_button = By.xpath("//button[@title='Save']");


    public SalesforceEditEmployee(WebDriver driver) {
        this.driver = driver;
    }

    public void editLinkClick() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(editLabel).click();
    }

    public void loadEditEmployeePage() {
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
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement label = driver.findElement(securityLabel);
        securityID = label.findElement(By.xpath("./following::input[1]"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(securityID));
        securityID.clear();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        securityID.sendKeys(securityid);
    }

    //save Edited Employee
    public void saveEditEmployee() {
        WebElement save = driver.findElement(save_button);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(save));
        save.click();
    }
}
