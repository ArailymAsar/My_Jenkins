package model;

import model.base.MainBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfigureUserPage extends MainBasePage {

    @FindBy(css = "input[name='_.fullName']")
    private WebElement inputFieldFullUserName;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    @FindBy(id = "yui-gen2-button")
    private WebElement buttonAddNewToken;

    public ConfigureUserPage(WebDriver driver) {
        super(driver);
    }

    public ConfigureUserPage clearInputFieldFullUserName() {
        inputFieldFullUserName.clear();

        return this;
    }

    public ConfigureUserPage inputNameInFieldFullUserName(String name) {
        inputFieldFullUserName.sendKeys(name);

        return this;
    }

    public StatusUserPage clickSaveButton() {
        saveButton.click();

        return new StatusUserPage(getDriver());
    }

    public String getAddNewTokenButtonName() {

        return buttonAddNewToken.getText();
    }
}
