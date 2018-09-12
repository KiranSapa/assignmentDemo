package assignment.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.assignment.Baseutils.BasePage;

public class ProductCartPage extends BasePage {
	
	private By productName=By.xpath("//h2[@class='product-name']/a");
	private By unitPrice=By.xpath("//td[@class='product-cart-price']/span/span");
	private By qtyDrp=By.xpath("//select[contains(@name,'cart')]");
	

	public ProductCartPage(WebDriver driver) {
		super(driver);
		waitForPage(5);
		// TODO Auto-generated constructor stub
	}

	public String getproductName(){
		log("getting product name");
		return getText(productName).trim();
		
	}
	
	
	public String getunitPrice(){
		log("Getting Unit Price");
		return getText(unitPrice).trim();
		
	}
	
	public String getQuantity(){
		log("getting qty");
		return getSelectedValueFromDrpDwn(qtyDrp).trim();
	}
}
