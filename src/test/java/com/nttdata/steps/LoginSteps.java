package com.nttdata.steps;

import com.nttdata.page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;

    public LoginSteps(WebDriver driver){
        this.driver = driver;
    }

    public void typeusername(String username) {
        WebElement userInputElement = driver.findElement(LoginPage.userInput);
        userInputElement.sendKeys(username);
    }

    public void typepassword(String password) {
        this.driver.findElement(LoginPage.passInput).sendKeys(password);
    }

    public void login() {
        this.driver.findElement(LoginPage.loginButton).click();
        verificarLoginExitoso();
    }
    public void verificarLoginExitoso() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            WebElement homeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.cerrar_sesion));
            Assertions.assertTrue(homeElement.isDisplayed());
            System.out.println("Login exitoso");
        } catch (TimeoutException e) {
            Assertions.fail("Login fallido (Credenciales Incorrectas). Deteniendo prueba");
        }
    }
}
