package assignment.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.assignment.Baseutils.BasePage;

public class ProductViewPage extends BasePage {
	
	private By productName=By.xpath("//div[@class='product-name']/span");
	private By sizeDropDown=By.className("product-size-click-btn");
	private By sizeNo=By.xpath("//a[@name='{1}']/span[contains(text(),'{1}')]");
	private By price=By.xpath("//span[@class='price']");
	private By btnAddTocart=By.xpath("//button[@title='Add to Cart']/span/span");
	private By drpQuantity=By.xpath("//select[@name='qty']");
	
	public ProductViewPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public String getProductName(){
		log("getting the text");
		return getText(productName);
	}
	
	public ProductViewPage selectSize(String size){
		log("Selecting Size"+size);
		safeClick(sizeDropDown);
		safeClick(replaceValues(sizeNo, size));
		return this;
		
	}
		
	
	public String getPrice(){
		log("Getting price");
		return getText(price);
	}
	
	public ProductViewPage selectQty(String qty){
		log("Selecting quantity");
		selectValuesFromDrpDwn(drpQuantity, qty);
		return this;
		
	}
	
	public ProductCartPage clickOnAddToCart(){
		log("clicking on add to cart btn");
		wait(5);
		click(btnAddTocart);
		return new ProductCartPage(driver);
	}
	
	
	
	
	

}
