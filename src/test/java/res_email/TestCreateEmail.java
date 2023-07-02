package res_email;

import core.BaseTest;
import org.junit.jupiter.api.Test;
import pageobject.MailboxCreationPage;
import pageobject.MainPage;

public class TestCreateEmail extends BaseTest {

    @Test
    public void createEmail() {
        MailboxCreationPage mailForm = new MainPage().clickCreateEmail();
        mailForm.clickButtonCreate();
        mailForm.fillInName();
        mailForm.fillInSurname();
        mailForm.fillFields();
    }
}
