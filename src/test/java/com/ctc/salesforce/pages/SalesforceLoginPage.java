package com.ctc.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesforceLoginPage {
    WebDriver driver = null;
    By username = By.id("username");
    By password = By.id("password");
    By login_Button = By.id("Login");
    By remember_me_checkBox = By.id("rememberUn");

    public SalesforceLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String user_name) {
        driver.findElement(username).sendKeys(user_name);
    }
    public void setPassword(String passwd) {
        driver.findElement(password).sendKeys(passwd);
    }

    public void logInButtonClick() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(login_Button));
        driver.findElement(login_Button).submit();
    }

    public void selectCheckBox() {
        WebElement checkBox = driver.findElement(remember_me_checkBox);
        if(!checkBox.isSelected()) {
            checkBox.click();
        }
    }

}
