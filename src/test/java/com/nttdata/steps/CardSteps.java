package com.nttdata.steps;

import com.nttdata.page.CardPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CardSteps {
    private WebDriver driver;
    public CardSteps(WebDriver driver) {
        this.driver = driver;
    }


    public void agregarUnidades(int unidades) {
        WebElement inputQuantity = driver.findElement(CardPage.quantity);
        inputQuantity.clear();
        inputQuantity.sendKeys(Keys.CONTROL + "a"); // Seleccionar todo el texto
        inputQuantity.sendKeys(Keys.BACK_SPACE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        inputQuantity.sendKeys(String.valueOf(unidades));
    }

    public boolean modalVisible(){
        this.driver.findElement(CardPage.addbutton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(CardPage.modalProductAgregate));
        return modal.isDisplayed();
    }

    public void validarMontoTotal() {
        WebElement precio = driver.findElement(CardPage.precio_compra);
        WebElement cantidad = driver.findElement(CardPage.cantidad_compra);
        WebElement montofinal = driver.findElement(CardPage.precio_final);

        double preciotfrm = Double.parseDouble(precio.getText().replace("S/", "").trim())  ;
        int cantidadfrm = Integer.parseInt(cantidad.getText().replace("Cantidad:", "").trim()) ;
        double montofinalfrm = Double.parseDouble(montofinal.getText().replace("S/", "").trim());

        if(preciotfrm * cantidadfrm == montofinalfrm){
            System.out.println("Validación del monto final del popup exitosa");
        }
    }

    public void finalizarCompra() {
        this.driver.findElement(CardPage.finalizar_compra).click();
    }

    public boolean titleVisible(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(CardPage.title_carrito));
        return title.isDisplayed();
    }

    public void validarMontoCarrito(){
        WebElement precio = driver.findElement(CardPage.precio_carrito);
        WebElement cantidad = driver.findElement(CardPage.cantidad_carrito);
        WebElement montofinal = driver.findElement(CardPage.precio_final_carrito);
        double preciof = Double.parseDouble(precio.getText().replace("S/","").trim());
        int cantidadf = Integer.parseInt(cantidad.getAttribute("value"));
        double montofinalf = Double.parseDouble(montofinal.getText().replace("S/","").trim());

        if(preciof * cantidadf == montofinalf){
            System.out.println("Validación del monto final del carrito exitosa");
        }

    }
}
