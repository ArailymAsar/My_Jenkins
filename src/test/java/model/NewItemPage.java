package model;

import model.base.MainBasePage;
import model.folder.FolderConfigPage;
import model.folder.FolderStatusPage;
import model.freestyle.FreestyleProjectConfigPage;
import model.multibranch_pipeline.MultibranchPipelineConfigPage;
import model.multiconfiguration.MultiConfigurationProjectConfigPage;
import model.organization_folder.OrgFolderConfigPage;
import model.pipeline.PipelineConfigPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;

import java.util.List;
import java.util.stream.Collectors;

public class NewItemPage extends MainBasePage {

    @FindBy(id = "name")
    private WebElement itemName;

    @FindBy(id = "itemname-required")
    private WebElement itemNameRequiredMsg;

    @FindBy(id = "itemname-invalid")
    private WebElement itemNameInvalidMsg;

    @FindBy(xpath = "//div[@class='icon']")
    private List<WebElement> itemsList;

    @FindBy(xpath = "//label/span[@class='label']")
    private List<WebElement> namesItemsList;

    @FindBy(id = "ok-button")
    private WebElement okButton;

    @FindBy(className = "hudson_model_FreeStyleProject")
    private WebElement freestyleProject;

    @FindBy(className = "com_cloudbees_hudson_plugins_folder_Folder")
    private WebElement folderType;

    @FindBy(xpath = "//li[@class = 'jenkins_branch_OrganizationFolder']")
    private WebElement orgFolder;

    @FindBy(xpath = "//span[contains(text(), 'Multi-configuration project')]")
    private WebElement multiConfigurationProject;

    @FindBy(xpath = "//li[@class='org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject']")
    private WebElement multibranchPipeline;

    @FindBy(xpath = "//span[text() = 'Pipeline']")
    private WebElement pipeline;

    @FindBy(id = "from")
    private WebElement copyFrom;

    @FindBy(className = "h3")
    private WebElement h3Header;

    public NewItemPage(WebDriver driver) {
        super(driver);
    }

    public NewItemPage clearItemName() {
        itemName.clear();

        return this;
    }

    public NewItemPage setItemName(String name) {
        getWait(2).until(ExpectedConditions.visibilityOf(itemName)).sendKeys(name);

        return this;
    }

    public NewItemPage selectFreestyleProject() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(freestyleProject)).click();

        return this;
    }

    public FreestyleProjectConfigPage selectFreestyleProjectAndClickOk() {
        selectFreestyleProject();
        okButton.submit();

        return new FreestyleProjectConfigPage(getDriver());
    }

    public CreateItemErrorPage selectFreestyleProjectAndClickOkWithError() {
        selectFreestyleProject();
        okButton.submit();

        return new CreateItemErrorPage(getDriver());
    }

    public FolderConfigPage selectFolderAndClickOk() {
        folderType.click();
        okButton.submit();

        return new FolderConfigPage(getDriver());
    }

    public CreateItemErrorPage selectExistFolderAndClickOk() {
        folderType.click();
        okButton.submit();

        return new CreateItemErrorPage(getDriver());
    }

    public OrgFolderConfigPage selectOrgFolderAndClickOk() {
        orgFolder.click();
        okButton.submit();

        return new OrgFolderConfigPage(getDriver());
    }

    public MultiConfigurationProjectConfigPage selectMultiConfigurationProjectAndClickOk() {
        multiConfigurationProject.click();
        okButton.submit();

        return new MultiConfigurationProjectConfigPage(getDriver());
    }

    public NewItemPage setItem(int index) {
        getAction().scrollByAmount(0, 300).perform();
        itemsList.get(index).click();

        return this;
    }

    public int getItemsListSize() {
        getWait(5).until(ExpectedConditions.visibilityOfAllElements(itemsList));
        return itemsList.size();
    }

    public NewItemPage selectMultibranchPipeline() {
        getWait(1).until(ExpectedConditions.visibilityOf(multibranchPipeline));
        multibranchPipeline.click();

        return this;
    }

    public MultibranchPipelineConfigPage selectMultibranchPipelineAndClickOk() {
        multibranchPipeline.click();
        okButton.submit();

        return new MultibranchPipelineConfigPage(getDriver());
    }

    public String getItemNameRequiredMsg() {
        return itemNameRequiredMsg.getText();
    }

    public String getItemNameInvalidMsg() {

        return getWait(2).until(ExpectedConditions.visibilityOf(itemNameInvalidMsg)).getText();
    }

    public boolean isOkButtonEnabled() {
        return okButton.isEnabled();
    }

    public PipelineConfigPage selectPipelineAndClickOk() {
        pipeline.click();
        okButton.submit();

        return new PipelineConfigPage(getDriver());
    }

    public NewItemPage selectPipeline() {
        pipeline.click();

        return this;
    }

    public NewItemPage setCopyFrom(String name) {
        getAction().moveToElement(copyFrom).click().sendKeys(name).perform();

        return this;
    }

    public CreateItemErrorPage clickOkButton() {
        okButton.click();

        return new CreateItemErrorPage(getDriver());
    }

    public PipelineConfigPage clickOk() {
        okButton.click();

        return new PipelineConfigPage(getDriver());
    }

    public NewItemPage setCopyFromItemName(String name) {
        TestUtils.scrollToEnd(getDriver());
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(copyFrom)).sendKeys(name);

        return this;
    }

    public MultiConfigurationProjectConfigPage clickOK() {
        okButton.click();

        return new MultiConfigurationProjectConfigPage(getDriver());
    }

    public FolderStatusPage clickOKButton() {
        okButton.click();

        return new FolderStatusPage(getDriver());
    }

    public MultibranchPipelineConfigPage clickOkMultibranchPipeline() {
        okButton.click();

        return new MultibranchPipelineConfigPage(getDriver());
    }

    public CreateItemErrorPage clickOKCreateItemErrorPage() {
        okButton.click();

        return new CreateItemErrorPage(getDriver());
    }

    public boolean isDisplayedFieldCopyFrom() {
        try {
            TestUtils.scrollToEnd(getDriver());
            getWait(5).until(ExpectedConditions.visibilityOf(itemName));
            return copyFrom.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getH3HeaderText() {

        return getWait(10).until(ExpectedConditions.visibilityOf(h3Header)).getText();
    }

    public List<String> newItemsNameList() {
        getWait(5).until(ExpectedConditions.visibilityOf(h3Header));

        return  namesItemsList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
