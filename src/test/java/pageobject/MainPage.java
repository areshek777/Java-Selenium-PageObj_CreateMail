package pageobject;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {
    @FindBy(xpath = "//a[@data-testid = 'mailbox-create-link']")
    private WebElement createEmail;

    public MainPage() {
        driver.get(getEmail());
        PageFactory.initElements(driver, this);
    }

    public MailboxCreationPage clickCreateEmail() {
        createEmail.click();
        return new MailboxCreationPage();
    }
}
