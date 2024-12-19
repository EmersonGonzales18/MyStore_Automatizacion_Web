package com.nttdata.stepsdefinitions;

import com.nttdata.steps.CardSteps;
import com.nttdata.steps.HomeSteps;
import com.nttdata.steps.InventorySteps;
import com.nttdata.steps.LoginSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class MyStoreSetpsDef {

    private WebDriver driver;

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPaginaDeLaTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/");
        HomeSteps homeSteps = new HomeSteps(driver);
        homeSteps.redireccionLogin();
        screenShot();
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String username, String password) {
        LoginSteps loginstep = new LoginSteps(driver);
        loginstep.typeusername(username);
        loginstep.typepassword(password);
        loginstep.login();
        screenShot();
    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        HomeSteps homeSteps = new HomeSteps(driver);
        homeSteps.redireccionarMenu_Submenu(categoria, subcategoria);
        screenShot();
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int unidades) {
        InventorySteps inventory = new InventorySteps(driver);
        inventory.getFirsItem();
        CardSteps cardSteps = new CardSteps(driver);
        cardSteps.agregarUnidades(unidades);
        screenShot();
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmacionDelProductoAgregado() {
        CardSteps cardSteps = new CardSteps(driver);
        cardSteps.modalVisible();
        screenShot();
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        CardSteps cardSteps = new CardSteps(driver);
        cardSteps.validarMontoTotal();
        screenShot();
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        CardSteps cardSteps = new CardSteps(driver);
        cardSteps.finalizarCompra();
        screenShot();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        CardSteps cardSteps = new CardSteps(driver);
        cardSteps.titleVisible();
        screenShot();
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        CardSteps cardSteps = new CardSteps(driver);
        cardSteps.validarMontoCarrito();
        screenShot();
    }
}
