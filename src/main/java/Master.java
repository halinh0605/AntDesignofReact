import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Master {
    static WebDriver webDriver;

    @BeforeClass
    public static void  setUpClass() {
        System.out.println("Master setup");
        System.setProperty("webdriver.chrome.driver", "D:\\setup\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    protected String randomCharacter(int length){
        String result = "";
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < length; i++) {
            result += (alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return result;
    }
    void selectList (String xpath, String value){
        webDriver.findElement(By.xpath(xpath)).click();
        String optionSelect = String.format("//select[@id='select-demo']//option[text()='%s']", value);
        WebElement optionXpath = webDriver.findElement(By.xpath(optionSelect));
        waitForElement(optionSelect);
        optionXpath.click();
    }
    protected String getRandomFromList(String list){
        String temp[] = list.split(";");
        Random ran = new Random();
        return temp[ran.nextInt(temp.length)];
    }

    void selecMultitList (String xpath, String value){
        webDriver.findElement(By.xpath(xpath)).click();
        String optionSelect = String.format("//select[@id='multi-select']//option[text()='%s']", value);
        WebElement optionXpath = webDriver.findElement(By.xpath(optionSelect));
        waitForElement(optionSelect);
        optionXpath.click();
    }

    void DropDown (String xpath, String value){
        webDriver.findElement(By.xpath(xpath)).click();
        String optionSelect = String.format("//select[@id='select2-country-container']//option[text()='%s']", value);
        WebElement optionXpath = webDriver.findElement(By.xpath(optionSelect));
        waitForElement(optionSelect);
        optionXpath.click();
    }


    void waitForElement(String xpath) {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    void selectDropdown(String xpath, String value) {
        webDriver.findElement(By.xpath(xpath)).click();
        String optionStr =String.format("//div[contains(@class,'ant-select-dropdown')]//ul[@role='listbox']//li[text()='%s']", value) ;
        WebElement optionXpath = webDriver.findElement(
                By.xpath(optionStr));
        waitForElement(optionStr);
        optionXpath.click();
    }

    void transfer(String xpath, String value){
        String optionTrans = String.format("//section[@id='components-transfer-demo-basic']//span[text()='%s']",value);
        WebElement optionXpath = webDriver.findElement(By.xpath(optionTrans));
        optionXpath.click();
    }

    /**
     * @param xpath
     * @param value  format like this:"value1;value2"
     * @param isRightToLeft True is click Right to left
     */
    protected void selectTransfer(String xpath, String value, boolean isRightToLeft){
        String[] temp = value.split(";");
        for(String s:temp){
            String optionTrans = String.format(xpath+"//span[text()='%s']",s);
            clickElement(optionTrans);
        }

        if(isRightToLeft){
            waitForElement(xpath+"//i[contains(@class,'anticon-right')]");
            clickElementUseJS(xpath+"//i[contains(@class,'anticon-right')]");
        }else {
            waitForElement(xpath+"//i[contains(@class,'anticon-left')]");
            clickElementUseJS(xpath+"//i[contains(@class,'anticon-left')]");
        }
    }

    protected void clickElement(String xpath){
        WebElement myElement = webDriver.findElement(By.xpath(xpath));
        myElement.click();
    }

    protected void clickElementUseJS(String xpath){
        WebElement myelement = webDriver.findElement(By.xpath(xpath));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", myelement);
    }

    protected void clickElementUseJSByid(String id){
        WebElement myelement = webDriver.findElement(By.id(id));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", myelement);
    }

    protected void scrollToElement(String xpath){
        WebElement myelement = webDriver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", myelement);
    }


    protected void inputTextToElement(String xpath, String value){
        WebElement optionXpath = webDriver.findElement(By.xpath(xpath));
        optionXpath.sendKeys(value);
    }

    void selectListBox(String xpath, String value) {
        webDriver.findElement(By.xpath(xpath)).click();
        String optionStr =String.format("//div[@class='dual-list list-left col-md-5']//ul[@class='list-group']//li[contains(.,'%s')]", value) ;
        WebElement optionXpath = webDriver.findElement(
                By.xpath(optionStr));
        waitForElement(optionStr);
        optionXpath.click();
    }



}
