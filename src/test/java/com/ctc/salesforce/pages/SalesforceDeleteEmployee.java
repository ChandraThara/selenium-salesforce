package com.ctc.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SalesforceDeleteEmployee {
    WebDriver driver = null;
    By delete_button = By.xpath("//button[@title='Delete']");


    public SalesforceDeleteEmployee(WebDriver driver) {
        this.driver = driver;
    }


    public void loadDeleteEmployeeAlert() {
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

    //delete Employee
    public void deleteEmployee() {
        WebElement delete = driver.findElement(delete_button);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(delete));
        delete.click();
    }
}
