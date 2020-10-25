import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Master {
    static WebDriver webDriver;
    private String PATH_RESULT = "TEST_RESULT";

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Master setup");
//        System.setProperty("webdriver.chrome.whitelistedIps", "");
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver86");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("close chrome");
        webDriver.close();
        webDriver.quit();
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println("Pass!");

        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("Fail!");
            takeScreenShot(description);

        }
    };

    private void takeScreenShot(Description desc) {
        String methodName = desc.getMethodName();

        try {
            String pathOut = PATH_RESULT + File.separator + desc.getTestClass().getSimpleName();
            File destinyFolder = new File(pathOut);
            if (!destinyFolder.exists()) {
                destinyFolder.mkdir();
            }
            File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
            FileUtils.copyFile(screenshotFile, new File(pathOut + File.separator + methodName + sdf.format(now) + ".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeScreenShot() {
        try {
            String pathOut = PATH_RESULT + File.separator + "DEBUG";
            File destinyFolder = new File(pathOut);
            if (!destinyFolder.exists()) {
                destinyFolder.mkdir();
            }
            File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
            FileUtils.copyFile(screenshotFile, new File(pathOut + File.separator +
                    sdf.format(now.getTime()) + ".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String randomCharacter(int length) {
        String result = "";
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < length; i++) {
            result += (alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return result;
    }

    void selectList(String xpath, String value) {
        webDriver.findElement(By.xpath(xpath)).click();
        String optionSelect = String.format("//select[@id='select-demo']//option[text()='%s']", value);
        WebElement optionXpath = webDriver.findElement(By.xpath(optionSelect));
        waitForElement(optionSelect);
        optionXpath.click();
    }

    protected String getRandomFromList(String list) {
        String temp[] = list.split(";");
        Random ran = new Random();
        return temp[ran.nextInt(temp.length)];
    }

    void selecMultitList(String xpath, String value) {
        webDriver.findElement(By.xpath(xpath)).click();
        String optionSelect = String.format("//select[@id='multi-select']//option[text()='%s']", value);
        WebElement optionXpath = webDriver.findElement(By.xpath(optionSelect));
        waitForElement(optionSelect);
        optionXpath.click();
    }

    void DropDown(String xpath, String value) {
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
        waitForElement(xpath);
        webDriver.findElement(By.xpath(xpath)).click();
        String optionStr = String.format("//div[contains(@class,'ant-select-dropdown')]//ul[@role='listbox']//li[text()='%s']", value);
        WebElement optionXpath = webDriver.findElement(
                By.xpath(optionStr));
        waitForElement(optionStr);
        optionXpath.click();
    }

    void transfer(String xpath, String value) {
        String optionTrans = String.format("//section[@id='components-transfer-demo-basic']//span[text()='%s']", value);
        WebElement optionXpath = webDriver.findElement(By.xpath(optionTrans));
        optionXpath.click();
    }

    /**
     * @param xpath
     * @param value         format like this:"value1;value2"
     * @param isRightToLeft True is click Right to left
     */
    protected void selectTransfer(String xpath, String value, boolean isRightToLeft) {
        String[] temp = value.split(";");
        for (String s : temp) {
            String optionTrans = String.format(xpath + "//span[text()='%s']", s);
            clickElement(optionTrans);
        }

        if (isRightToLeft) {
            waitForElement(xpath + "//i[contains(@class,'anticon-right')]");
            clickElementUseJS(xpath + "//i[contains(@class,'anticon-right')]");
        } else {
            waitForElement(xpath + "//i[contains(@class,'anticon-left')]");
            clickElementUseJS(xpath + "//i[contains(@class,'anticon-left')]");
        }
    }

    protected void clickElement(String xpath) {
        WebElement myElement = webDriver.findElement(By.xpath(xpath));
        myElement.click();
    }

    protected void clickElementUseJS(String xpath) {
        WebElement myelement = webDriver.findElement(By.xpath(xpath));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", myelement);
    }

    protected void clickElementUseJSByid(String id) {
        WebElement myelement = webDriver.findElement(By.id(id));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", myelement);
    }

    protected void scrollToElement(String xpath) {
        WebElement myelement = webDriver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", myelement);
    }


    protected void inputTextToElement(String xpath, String value) {
        WebElement optionXpath = webDriver.findElement(By.xpath(xpath));
        optionXpath.sendKeys(value);
    }

    void selectListBox(String xpath, String value) {
        webDriver.findElement(By.xpath(xpath)).click();
        String optionStr = String.format("//div[@class='dual-list list-left col-md-5']//ul[@class='list-group']//li[contains(.,'%s')]", value);
        WebElement optionXpath = webDriver.findElement(
                By.xpath(optionStr));
        waitForElement(optionStr);
        optionXpath.click();
    }
}
