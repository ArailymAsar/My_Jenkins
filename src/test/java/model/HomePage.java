package model;

import model.base.BaseStatusPage;
import model.base.MainBasePage;
import model.base.side_menu.HomeSideMenuComponent;
import model.folder.FolderConfigPage;
import model.folder.FolderStatusPage;
import model.freestyle.FreestyleProjectConfigPage;
import model.freestyle.FreestyleProjectStatusPage;
import model.multibranch_pipeline.MultibranchPipelineStatusPage;
import model.multiconfiguration.MultiConfigurationProjectStatusPage;
import model.organization_folder.OrgFolderStatusPage;
import model.pipeline.PipelineConfigPage;
import model.pipeline.PipelineStatusPage;
import model.views.MyViewsPage;
import model.views.NewViewPage;
import model.views.ViewPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;
import java.util.List;
import java.util.stream.Collectors;
import static runner.TestUtils.scrollToElement;

public class HomePage extends MainBasePage {

    @FindBy(css = "tr td a.model-link")
    private List<WebElement> jobList;

    @FindBy(linkText = "Configure")
    private WebElement configureDropDownMenu;

    @FindBy(linkText = "Rename")
    private WebElement renameDropDownMenu;

    @FindBy(xpath = "//li[@index='2']")
    private WebElement deleteButtonInDropDownMenu;

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(css = ".item a[class=''][href$='/my-views/']")
    private WebElement myViewsTopMenuLink;

    @FindBy(css = ".tabBar>.tab>a.addTab")
    private WebElement addViewLink;

    @FindBy(xpath = "//span[text()='Move']")
    private WebElement moveButtonDropdown;

    @FindBy(xpath = "//div[@class='tabBar']/div/a")
    private List<WebElement> viewList;

    @FindBy(css = "#page-header .jenkins-menu-dropdown-chevron")
    private WebElement userDropdownMenu;

    @FindBy(css = ".first-of-type > .yuimenuitem")
    private List<WebElement> userDropdownMenuItems;

    @FindBy(xpath = "//span[text()='Edit View']/..")
    private WebElement editView;

    @FindBy(linkText = "Builds")
    private WebElement buildsItemInUserDropdownMenu;

    @FindBy(xpath = "//span[contains(@class, 'build-status-icon')]/span/child::*")
    private WebElement buildStatusIcon;

    @FindBy(xpath = "(//*[local-name()='svg' and @tooltip='Disabled'])[2]")
    private WebElement projectDisabledIcon;

    @FindBy(id = "description-link")
    private WebElement addDescriptionButton;

    @FindBy(xpath = "//div[@id='description']//textarea")
    private WebElement descriptionTextarea;

    @FindBy(linkText = "Configure")
    private WebElement configureItemInUserDropdownMenu;

    @FindBy(linkText = "My Views")
    private WebElement myViewItemInUserDropdownMenu;

    @FindBy(linkText = "Credentials")
    private WebElement credentialsItemInUserDropdownMenu;

    @FindBy(xpath = "(//a[@class='yuimenuitemlabel'])[3]/span")
    private WebElement buildNowButton;

    @FindBy(id = "search-box")
    private WebElement searchField;

    @FindBy(css = "#projectstatus th")
    private List<WebElement> listJobTableHeaders;

    @FindBy(css = "[href*='/user/']")
    private WebElement user;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomeSideMenuComponent getSideMenuFrame() {
        return new HomeSideMenuComponent(getDriver());
    }

    public NewViewPage clickAddViewLink() {
        addViewLink.click();

        return new NewViewPage(getDriver());
    }

    public List<String> getJobNamesList() {
        return jobList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getNumberOfJobsContainingString(String string) {

        return (int) jobList
                .stream()
                .filter(element -> element.getText().contains(string))
                .count();
    }

    public List<String> getViewList() {
        return viewList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public FreestyleProjectStatusPage clickFreestyleProjectName() {
        getWait(10).until(ExpectedConditions.visibilityOfAllElements(jobList)).get(0).click();

        return new FreestyleProjectStatusPage(getDriver());
    }

    public FreestyleProjectStatusPage clickFreestyleProjectName(String name) {
        getWait(10).until(ExpectedConditions.elementToBeClickable(By.linkText(name))).click();

        return new FreestyleProjectStatusPage(getDriver());
    }

    public RenameItemPage<PipelineStatusPage> clickRenamePipelineDropDownMenu() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(renameDropDownMenu)).click();

        return new RenameItemPage<>(getDriver(), new PipelineStatusPage(getDriver()));
    }

    public RenameItemPage<MultiConfigurationProjectStatusPage> clickRenameMultiConfigurationDropDownMenu() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(renameDropDownMenu)).click();

        return new RenameItemPage<>(getDriver(), new MultiConfigurationProjectStatusPage(getDriver()));
    }

    public RenameItemPage<FolderStatusPage> clickRenameFolderDropDownMenu() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(renameDropDownMenu)).click();

        return new RenameItemPage<>(getDriver(), new FolderStatusPage(getDriver()));
    }

    public RenameItemPage<MultibranchPipelineStatusPage> clickRenameMultibranchPipelineDropDownMenu() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(renameDropDownMenu)).click();

        return new RenameItemPage<>(getDriver(), new MultibranchPipelineStatusPage(getDriver()));
    }

    public ConfigurationGeneralPage clickConfigDropDownMenu() {
        getWait(6).until(ExpectedConditions.elementToBeClickable(configureDropDownMenu)).click();

        return new ConfigurationGeneralPage(getDriver());
    }

    public PipelineStatusPage clickPipelineProjectName() {
        jobList.get(0).click();

        return new PipelineStatusPage(getDriver());
    }

    public DeletePage<HomePage> clickDeleteDropDownMenu() {
        getWait(3).until(ExpectedConditions.elementToBeClickable(deleteButtonInDropDownMenu));
        deleteButtonInDropDownMenu.click();

        return new DeletePage<>(getDriver(), this);
    }

    public HomePage clickJobDropDownMenu(String name) {
        getWait(5).until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(
                "//tr[@id='job_%s']//button[@class='jenkins-menu-dropdown-chevron']", name)))).click();

        return this;
    }

    public MultibranchPipelineStatusPage clickJobMBPipeline(String name) {
        getDriver().findElement(By.xpath("//span[text()='" + name + "']")).click();

        return new MultibranchPipelineStatusPage(getDriver());
    }

    public PipelineConfigPage clickConfigureDropDownMenu() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(configureDropDownMenu)).click();

        return new PipelineConfigPage(getDriver());
    }

    public String getHeaderText() {

        return getWait(10).until(ExpectedConditions.visibilityOf(header)).getText();
    }

    public HomePage clickJobDropdownMenu(String folderName) {
        getWait(5).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[@href='job/" + folderName + "/']/button"))).click();

        return this;
    }

    public FolderStatusPage clickFolder(String folderName) {
        getDriver().findElement(By.xpath("//span[text()='" + folderName + "']")).click();

        return new FolderStatusPage(getDriver());
    }

    public FolderConfigPage clickConfigureDropDownMenuForFolder() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(configureDropDownMenu)).click();

        return new FolderConfigPage(getDriver());
    }

    public String getJobBuildStatus(String name) {
        return getDriver().findElement(By.id(String.format("job_%s", name)))
                .findElement(By.xpath(".//*[name()='svg']")).getAttribute("tooltip");
    }

    public MyViewsPage clickMyViewsTopMenuLink() {
        myViewsTopMenuLink.click();

        return new MyViewsPage(getDriver());
    }

    public MultiConfigurationProjectStatusPage clickMultiConfigurationProject(String name) {
        getWait(5).until(ExpectedConditions.elementToBeClickable(By.linkText(name))).click();
        return new MultiConfigurationProjectStatusPage(getDriver());
    }

    public <T extends BaseStatusPage<T, ?>> MovePage<T> clickMoveButtonDropdown(T baseStatusPage) {
        getWait(5).until(ExpectedConditions.visibilityOf(moveButtonDropdown));
        scrollToElement(getDriver(), moveButtonDropdown);
        moveButtonDropdown.click();
        return new MovePage<>(getDriver(), baseStatusPage);
    }

    public HomePage clickUserDropdownMenu() {
        userDropdownMenu.click();

        return this;
    }

    public int getItemsCountInUserDropdownMenu() {
        int itemsCount = 0;
        for (WebElement item : getWait(5).until(
                ExpectedConditions.visibilityOfAllElements(
                        userDropdownMenuItems))) {
            itemsCount++;
        }

        return itemsCount;
    }

    public String getItemsNamesInUserDropdownMenu() {
        StringBuilder itemsNames = new StringBuilder();
        for (WebElement item : getWait(5).until(
                ExpectedConditions.visibilityOfAllElements(
                        userDropdownMenuItems))) {
            itemsNames.append(item.getText()).append(" ");
        }

        return itemsNames.toString().trim();
    }

    public BuildsUserPage clickBuildsItemInUserDropdownMenu() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(
                buildsItemInUserDropdownMenu)).click();

        return new BuildsUserPage(getDriver());
    }

    public String getBuildDurationTime() {
        if (getJobBuildStatus().equals("Success")) {

            return getDriver().findElement(By.xpath("//tr[contains(@class, 'job-status')]/td[4]")).getText();
        } else if (getJobBuildStatus().equals("Failed")) {

            return getDriver().findElement(By.xpath("//tr[contains(@class, 'job-status')]/td[5]")).getText();
        }

        return null;
    }

    public String getJobBuildStatus() {

        return buildStatusIcon.getAttribute("tooltip");
    }

    public ViewPage clickView(String name) {
        getDriver().findElement(By.xpath(String.format("//a[@href='/view/%s/']", name))).click();

        return new ViewPage(getDriver());
    }

    public String getLastSuccessText(String name) {

        return getDriver().findElement(By.xpath(String.format("//*[@id='job_%s']/td[4]", name))).getText();
    }

    public String getJobName(String name) {

        return getDriver().findElement(By.xpath(String.format("//span[contains(text(),'%s')]", name))).getText();
    }

    public Boolean getProjectIconText() {
        return projectDisabledIcon.isDisplayed();
    }

    public String getStatusBuildText() {

        return buildStatusIcon.getAttribute("tooltip");
    }

    public HomePage movePointToCheckBox() {
        getAction().moveToElement(buildStatusIcon).perform();

        return this;
    }

    public FreestyleProjectConfigPage clickConfigDropDownMenuFreestyle() {
        getWait(6).until(ExpectedConditions.elementToBeClickable(configureDropDownMenu)).click();

        return new FreestyleProjectConfigPage(getDriver());
    }

    public HomePage clickAddDescriptionButton() {
        getWait(10).until(ExpectedConditions.elementToBeClickable(addDescriptionButton)).click();
        getWait(10).until(ExpectedConditions.visibilityOf(descriptionTextarea));

        return this;
    }

    public boolean isDescriptionTextareaEnabled() {

        return descriptionTextarea.isEnabled();
    }

    public boolean isAddDescriptionButtonEnabled() {

        return addDescriptionButton.isEnabled();
    }

    public HomePage waitForVisibilityOfAddDescriptionButton() {
        getWait(10).until(ExpectedConditions.visibilityOf(addDescriptionButton));

        return this;
    }

    public boolean isAddDescriptionButtonPresent() {
        try {
            getDriver().findElement(By.id("description-link"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDescriptionTextareaPresent() {
        try {
            getDriver().findElement(By.xpath("//div[@id='description']//textarea"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public ConfigureUserPage clickConfigureItemInUserDropdownMenu() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(
                configureItemInUserDropdownMenu)).click();

        return new ConfigureUserPage(getDriver());
    }

    public MyViewsPage clickMyViewItemInUserDropdownMenu() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(
                myViewItemInUserDropdownMenu)).click();

        return new MyViewsPage(getDriver());
    }

    public CredentialsPage clickCredentialsItemInUserDropdownMenu() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(
                credentialsItemInUserDropdownMenu)).click();

        return new CredentialsPage(getDriver());
    }

    public HomePage clickProjectDropdownMenu(String projectName) {
        getWait(5).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[@href='job/" + projectName + "/']/button"))).click();

        return this;
    }

    public boolean buildNowButtonIsDisplayed() {

        return getWait(5).until(ExpectedConditions.visibilityOf(buildNowButton)).isDisplayed();
    }

    public OrgFolderStatusPage clickOrgFolder(String name) {
        getDriver().findElement(By.linkText(name)).click();

        return new OrgFolderStatusPage(getDriver());
    }

    public PipelineStatusPage clickPipelineJob(String name) {
        getDriver().findElement(By.xpath("//span[text()='" + name + "']")).click();

        return new PipelineStatusPage(getDriver());
    }

    public String getJobListAsString() {
        StringBuilder listProjectsNames = new StringBuilder();
        for (WebElement projects : jobList) {
            listProjectsNames.append(projects.getText()).append(" ");
        }

        return listProjectsNames.toString().trim();
    }

    public MultiConfigurationProjectStatusPage setSearchAndClickEnter(String request) {
        searchField.sendKeys(request);
        getWait(3).until(TestUtils.ExpectedConditions.elementIsNotMoving(searchField)).sendKeys(Keys.ENTER);

        return new MultiConfigurationProjectStatusPage(getDriver());
    }

    public int getJobTableHeadersSize() {
        return listJobTableHeaders.size();
    }

    public String getUserName() {
        return user.getText();
    }
}