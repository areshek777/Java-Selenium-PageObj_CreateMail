package pageobject;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;
import java.util.stream.Collectors;

public class MailboxCreationPage extends BasePage {
    @FindBy(xpath = "//input[@name ='fname']")
    private WebElement fieldName;
    @FindBy(xpath = "//input[@name ='lname']")
    private WebElement fieldSurname;
    @FindBy(xpath = "//div[@class='Select__value-container css-0']/span[text() = 'День']/parent::div")
    private WebElement day;
    @FindBy(xpath = "//div[@class='Select__value-container css-0']/span[text() = 'Месяц']/parent::div")
    private WebElement month;
    @FindBy(xpath = "//div[@class='Select__value-container css-0']/span[text() = 'Год']/parent::div")
    private WebElement year;
    @FindBy(xpath = "//div[@data-test-id = 'select-value:1']")
    private WebElement number;
    @FindBy(xpath = "//div[@data-test-id = 'select-value:5']")
    private WebElement numberMonth;
    @FindBy(xpath = "//div[@data-test-id = 'select-value:2000'] ")
    private WebElement numberYear;
    @FindBy(xpath = "//input[@value='male']/following::div[2]")
    private WebElement gender;
    @FindBy(xpath = "//input[@name='username']")
    private WebElement userName;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;
    @FindBy(xpath = "//input[@name = 'repeatPassword']")
    private WebElement repeatPassword;
    @FindBy(xpath = "//form/button")
    private WebElement buttonCreate;
    @FindBy(xpath = "//div[@data-test-id]/div[3]/small[contains(@class, '')]")
    private List<WebElement> errorsFields = new ArrayList<>();
    @FindBy(xpath = "//div[@data-test-id]//label[text()]")
    private List<WebElement> textsFields = new ArrayList<>();
    @FindBy(xpath = "//h3")
    WebElement formName;

    public MailboxCreationPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickButtonCreate() {
        focusPage();
        buttonCreate.click();
        addMapTextError();
    }

    public void fillInName() {
        fieldName.sendKeys(getName(), Keys.ENTER);
        errorsFields.add(this.driver.findElement(By.xpath("//div/div[@data-test-id = 'error-footer-text']/div/div[3]/small")));
        addMapTextError();
    }

    public void fillInSurname() {
        fieldSurname.sendKeys(getSurname(), Keys.ENTER);
        errorsFields.add(this.driver.findElement(By.xpath("//div/div[@data-test-id = 'error-footer-text']/div/div[3]/small")));
        addMapTextError();
    }

    public void fillFields() {
        day.click();
        number.click();
        month.click();
        numberMonth.click();
        year.click();
        numberYear.click();
        gender.click();
        userName.sendKeys(getUsername());
        password.sendKeys(getPassword());
        repeatPassword.sendKeys(getPassword());
        buttonCreate.click();
        getFormName();
    }

    private void focusPage() {
        List<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    private void addMapTextError() {
        String surnameText = "фамилия";
        String surnameError = "укажите фамилию";
        Map<String, String> fillTextAndError = new HashMap<>();
        List<String> errorList = errorsFields.stream().map(str -> str.getText().toLowerCase()).collect(Collectors.toList());
        List<String> textList = textsFields.stream().map(str -> str.getText().toLowerCase()).collect(Collectors.toList());
        for (int i = 0; i < textList.size(); i++) {
            if (!textList.get(i).equals(surnameText)) {
                for (int j = 0; j < errorList.size(); j++) {
                    if (textList.get(i).endsWith(errorList.get(j).substring(errorList.get(j).lastIndexOf(" ") + 1))) {
                        fillTextAndError.put(textList.get(i), errorList.get(j));
                    }
                }
            } else {
                if (errorList.stream().anyMatch(surnameError::contains)) {
                    fillTextAndError.put(surnameText, surnameError);
                }
            }
        }
        fillTextAndError.forEach((key, value) -> System.out.println("Поле: " + key + " -> Ошибка: " + value));
        System.out.println();
    }

    private void getFormName() {
        System.out.print(formName.getText());
    }

}
//    List<String> tabs = new ArrayList(driver.getWindowHandles());
//        driver.switchTo().window(tabs.get(1));
//                buttonCreate.click();
//                String errorArray1 [] = new String[errorsFields.size()];
//                String textArray1 [] = new String[textsFields.size()];
//                List<String> errorArray = errorsFields.stream().map(str -> str.getText().toLowerCase()).collect(Collectors.toList());
//        List<String> textArray = textsFields.stream().map(str -> str.getText().toLowerCase()).collect(Collectors.toList());
//        //List<String> result = test.stream().filter(test1::contains).collect(Collectors.toList());
//        System.out.println(errorArray);
//        System.out.println(textArray);
//        //замена на коллекцию
////        for (int i = 0; i < errorArray.length; i++) {
////            errorArray[i] = errorsFields.get(i).getText().toLowerCase();
////
////        }
////        for(int i = 0; i < textArray.length; i++) {
////            textArray[i] = textsFields.get(i).getText().toLowerCase();
////        }
//        for(int i = 0; i < errorArray.size(); i++) {
//        for(int j = 0; j < errorArray.size(); j++) {
//        if(textArray.get(i).endsWith(errorArray.get(j).substring(errorArray.get(j).lastIndexOf( " ")+1))) {
//        example.put(textArray.get(j), errorArray.get(i));
//        }
//        }
//        }