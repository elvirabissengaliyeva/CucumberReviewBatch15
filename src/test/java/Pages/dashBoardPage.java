package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Utils.commonMethods.driver;

public class dashBoardPage {
    @FindBy (id = "menu_pim_viewPimModule")
    public WebElement PIMOption;
    public Alert welcomeText;

    public dashBoardPage(){

        PageFactory.initElements(driver,this);
    }
}
