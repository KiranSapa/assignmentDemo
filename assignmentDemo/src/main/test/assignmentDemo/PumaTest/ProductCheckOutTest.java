package assignmentDemo.PumaTest;

import org.testng.annotations.Test;

import com.assignment.Baseutils.BaseTest;

import assignment.pageObjects.ProductCartPage;
import assignment.pageObjects.ProductViewPage;
import assignment.pageObjects.PumaHomePage;
import assignment.pageObjects.ShoesPage;

public class ProductCheckOutTest extends BaseTest {
	String ShoeSize="6";
	String quantity="1";
	
	@Test
	public void test(){
		log("Step 1:");
		ShoesPage shoesPage=new PumaHomePage(driver).clickOnMenTab().clickonShoes("Running");
		
		log("Step2: Selecting Shoe");
		ProductViewPage productview=shoesPage.clickOnShoe(2);
		
		log("Step 3: adding product to the cart");
		String productName=productview.getProductName();
		String productPrice=productview.getPrice();
		ProductCartPage cartPage=productview.selectSize(ShoeSize).selectQty(quantity).clickOnAddToCart();
		
		log("Step 4: validating the cart");
		
		//validating prod name
		getsoftAssert().assertTrue(productName.toLowerCase().contains(cartPage.getproductName().toLowerCase()),"SHOES NAME DOESN'T MATCHING ");
		//Validating price
		getsoftAssert().assertEquals(cartPage.getunitPrice(), productPrice,"PRICE MISMATCHES");
		//validating quantity
		getsoftAssert().assertEquals(cartPage.getQuantity(),quantity,"QUANTITY MISMATCHES");
		//Asserting all
		getsoftAssert().assertAll();
		
		
	}
}
