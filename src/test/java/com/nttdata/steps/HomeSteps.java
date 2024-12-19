package com.nttdata.steps;

import com.nttdata.page.HomePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomeSteps {
    private WebDriver driver;

    public HomeSteps(WebDriver driver){
        this.driver = driver;
    }

    public void redireccionLogin(){
        this.driver.findElement(HomePage.menuLogin).click();
    }

    public void redireccionarMenu_Submenu(String categoria, String subcategoria) {
        String menuXpath = "//a[contains(., '"+categoria+"')]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(menuXpath)));
            Assertions.assertTrue(menu.isDisplayed());
            menu.click();
        } catch (TimeoutException e) {
            Assertions.fail("No se encontro el menu. Deteniendo prueba");
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/a[text()='" + subcategoria + "']")));
        String submenuXpath = "//li/a[text()='"+subcategoria+"']";
        this.driver.findElement(By.xpath(submenuXpath)).click();

    }


}

