package stepDefinitions;

import functionLibrary.CommonFunctions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BagSteps extends CommonFunctions {


    @Given("I am on home page {string}")
    public void openHomePage(String url) {
        goToUrl(driver, url);
        assertTrue(driver, By.className("login_logo"));
    }

    @When("I do Login with username {string} and {string}")
    public void doLogin(String username, String password) {
        type(driver, By.id("user-name"), username );
        type(driver, By.id("password"), password);
        clickElement(driver, By.name("login-button"));
        assertTrue(driver, By.className("title"));
    }

    @When("I choose a product {string}")
    public void chooseProduct(String productName) {
        driver.findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='" + productName + "']")).click();
        Assert.assertTrue(driver.findElement(By.name("back-to-products")).isDisplayed());
    }

    @When("I click Add to cart button with badge {string}")
    public void addToCart(String badgeVal) {
        clickElement(driver, By.name("add-to-cart"));
        assertEquals(driver, By.className("shopping_cart_badge"), badgeVal);
    }

    @When("I click bag icon")
    public void clickBagIcon() {
        driver.findElement(By.className("shopping_cart_link")).click();
        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());
    }

    @Then("I should see product {string} in the bag")
    public void verifyProductInBag(String expectedProductInBag) {
        Assert.assertEquals(expectedProductInBag, driver.findElement(By.className("inventory_item_name")).getText());
    }

    @When("I click remove button in the bag")
    public void clickRemoveBtn() {
        clickElement(driver, By.xpath("//button[text()='Remove']"));
    }

    @When("I click back button")
    public void clickBackBtn() {
        driver.findElement(By.id("back-to-products")).click();
        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());
    }

    @Then("the product {string} should be removed")
    public void verifyRemoveProductFromBag(String expProductToBeRem) {
        assertNoSuchElement(driver, By.className("inventory_item_name"));
    }

    @Then("I should see products {string} and {string} in the bag")
    public void VerifyMultiProductsInBag(String expectedProduct1InBag, String expectedProduct2InBag) {
        //Create an array of expected products
        String expectedProducts[] = {expectedProduct1InBag, expectedProduct2InBag};
        //create list actual product elements
        List<WebElement> actualProductsInBag = driver.findElements(By.xpath("//div[@data-test='inventory-item-name']"));
        //create an empty list to hold product names
        List<String> actualProductNames = new ArrayList<>();
        //iterate through actual product elements and get name from each and add it to empty list
        for (WebElement actualProduct : actualProductsInBag) {
            actualProductNames.add(actualProduct.getText());
        }
        //iterate through each expected products array and check that each exists with in list of actualProductNames
        for (String expectedProduct : expectedProducts) {
            Assert.assertTrue(actualProductNames.contains(expectedProduct));
        }


    }

}
