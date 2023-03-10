package model;

import model.base.MainBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuildsUserPage extends MainBasePage {

    @FindBy(css = "div#main-panel > h1")
    private WebElement headerH1;

    public BuildsUserPage(WebDriver driver) {

        super(driver);
    }

    public String getHeaderH1Text() {

        return headerH1.getText();
    }
}
