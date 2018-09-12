package assignment.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.assignment.Baseutils.BasePage;

public class ShoesPage extends BasePage {
	
	
	//Locators
	private By shoesLists=By.xpath("//ul[contains(@class,'products-grid')]/li");

	public ShoesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public ProductViewPage clickOnShoe(int shoeNo){
		log("clicking on the shoe"+shoeNo);
		driver.findElements(shoesLists).get(shoeNo-1).click();
		switchToNewWindow();
		return new ProductViewPage(driver);
	}
	
	
	
	
	
	
	
	
	

}
