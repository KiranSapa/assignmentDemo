package assignment.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.assignment.Baseutils.BasePage;

public class PumaHomePage extends BasePage{
	//Locators
	private By tabMen=By.xpath("//a[text()=' MEN ']");
	private By linkMen_Running=By.xpath("//li[@id='men-subnav']//li[p[a[text()='Shoes']]]/following-sibling::li/a[text()='{1}']");
	

	public PumaHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	
	public PumaHomePage clickOnMenTab(){
		log("Hovering on men tab");
		mouseHover(driver.findElement(tabMen));
		return this;
	}
	
	public ShoesPage clickonShoes(String shoesType){
		linkMen_Running=replaceValues(linkMen_Running, "Running");
		log("clicking on running shoes");
		waitForElementPresent(linkMen_Running);
		click(linkMen_Running);
		return new ShoesPage(driver);
	}
	
	
	
	
}
