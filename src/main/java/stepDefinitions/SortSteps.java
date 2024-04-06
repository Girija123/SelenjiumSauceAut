package stepDefinitions;

import functionLibrary.CommonFunctions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortSteps extends CommonFunctions {

    @When("I click sort dropdown")
    public void clickSortDropdown() {
        clickElement(driver, By.className("product_sort_container"));
    }

    @Then("I should see options {string}, {string}, {string}, {string}")
    public void verifySortDropDownOptionsExist(String expOpt1, String expOpt2, String expOpt3, String expOpt4) {
        String expDropDownOptns[] = {expOpt1, expOpt2, expOpt3, expOpt4};

        List<WebElement> options = new Select(findElement(driver, By.className("product_sort_container"))).getOptions();

        List<String> optionTxts = new ArrayList<>();

        for (WebElement option : options) {
            optionTxts.add(option.getText());
        }

        Assert.assertEquals(expDropDownOptns.length, optionTxts.size());

        for (String expOption : expDropDownOptns) {
            Assert.assertTrue(optionTxts.contains(expOption));
        }
    }

    @When("I choose option {string}")
    public void chooseSortOption(String option)
    {
        selectDrpDownOptn(driver, By.className("product_sort_container"), option);
    }

    @Then("I expect products to be sorted in ascending order")
    public void verifyProductNameAtoZ()
    {
        List<WebElement> actualProducts = findElements(driver, By.xpath("//div[@data-test='inventory-item-name']"));

        List<String> productNames = new ArrayList<>();
        for(WebElement actualProduct: actualProducts)
        {
            productNames.add(actualProduct.getText());
        }

        List<String> sortedProductNames = new ArrayList<>(productNames);
        Collections.sort(sortedProductNames);
        //Collections.sort(sortedProductNames, Collections.reverseOrder());

        Assert.assertTrue(productNames.equals(sortedProductNames));
        }
}
