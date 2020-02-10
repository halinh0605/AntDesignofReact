import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;

public class TestComponent extends Master {

    //ant - Select
    @Test
    public void testSelect() {
        webDriver.get("https://ant.design/components/select/");
        String xx = getRandomFromList("Lucy;Jack;Tom");
        selectDropdown("//*[@id='components-select-demo-search']//div", xx);
//        takeScreenShot();
        String result = webDriver.findElement(By.xpath("//*[@id='components-select-demo-search']//div[@class='ant-select-selection-selected-value']")).getText();

        assertEquals(result, "hfgf");
    }

    //ant - TreeSelect
    @Test
    public void testTreeSelect() {
        webDriver.get("https://ant.design/components/tree-select/");
        webDriver.findElement(By.xpath("//section[@id='components-tree-select-demo-basic']//section[@class='code-box-demo']//span[@class='ant-select-selection__placeholder']")).click();
        webDriver.findElement(By.xpath("//span[@class='ant-select-tree-title'][contains(.,'my leaf')]")).click();
        String result = webDriver.findElement(By.xpath("//span[@class='ant-select-selection__rendered'][contains(.,'my leaf')]")).getText();
        assertEquals(result, "my leaf");
    }

    //ant- Button
    @Test
    public void testButton() {
        webDriver.get("https://ant.design/components/button/");
        webDriver.findElement(By.xpath("//*[@id='components-button-demo-size']//span[contains(.,'Small')]")).click();
    }

    //ant - Cascader
    @Test
    public void testCascader() {
        webDriver.get("https://ant.design/components/cascader/");
        webDriver.findElement(By.xpath("//*[@id='components-cascader-demo-basic']//span[@class='ant-cascader-picker-label']")).click();
    }

    //ant - Datepicker
    @Test
    public void testDatepicker() {
        webDriver.get("https://ant.design/components/date-picker/");
        WebElement inputDate = webDriver.findElement(By.xpath("//*[@id='components-date-picker-demo-basic']//span[@class='ant-calendar-picker']//input[@placeholder='Select date']"));
        inputDate.click();
        webDriver.findElement(By.xpath("//input[@placeholder='Select date' and contains(@class, 'ant-calendar-input') ]")).sendKeys("2020-01-14");
    }

    //ant - Form
    @Test
    public void testForm1() {
        webDriver.get("https://ant.design/components/form/");
        webDriver.findElement(By.id("horizontal_login_username")).sendKeys("user");
        webDriver.findElement(By.id("horizontal_login_password")).sendKeys("12345");
        webDriver.findElement(By.xpath("//div[@class='ant-form-item-control']//span[@class='ant-form-item-children']//button[contains(.,'Log in')]")).click();
    }

    @Test
    public void testForm2() {
        webDriver.get("https://ant.design/components/form/");
        WebElement Element = webDriver.findElement(By.id("normal_login_username"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", Element);

        webDriver.findElement(By.id("normal_login_username")).sendKeys("user");
        webDriver.findElement(By.id("normal_login_password")).sendKeys("12345");
        webDriver.findElement(By.xpath("//button[@class='ant-btn login-form-button ant-btn-primary']")).click();
    }

    @Test
    public void testForm3() {
        webDriver.get("https://ant.design/components/form/");
        WebElement Element = webDriver.findElement(By.id("register_email"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", Element);

        webDriver.findElement(By.id("register_email")).sendKeys("linhha0606@gmail.com");
        webDriver.findElement(By.id("register_password")).sendKeys("12345");
        webDriver.findElement(By.id("register_confirm")).sendKeys("12345");
        webDriver.findElement(By.id("register_nickname")).sendKeys("banhbao");

        clickElementUseJS("//*[@id='register_residence']");
        waitForElement("//li[contains(@title,'Zhejiang')]");
        clickElementUseJS("//li[contains(@title,'Zhejiang')]");
        waitForElement("//li[contains(@title,'Hangzhou')]");
        clickElementUseJS("//li[contains(@title,'Hangzhou')]");
        waitForElement("//li[contains(@title,'West Lake')]");
        clickElementUseJS("//li[contains(@title,'West Lake')]");
        ;

        webDriver.findElement(By.xpath("//input[@id='register_phone']")).sendKeys("1234567");
        webDriver.findElement(By.xpath("//input[@class='ant-input ant-select-search__field']")).sendKeys("www.hoa.com");
        webDriver.findElement(By.id("register_captcha")).sendKeys("abc");
        webDriver.findElement(By.xpath("//button[contains(.,'Get captcha')]")).click();
        webDriver.findElement(By.id("register_agreement")).click();
        webDriver.findElement(By.xpath("//button[contains(.,'Register')]")).click();
    }

    //ant - InputNumber
    @Test
    public void testInputNumber() {
        webDriver.get("https://ant.design/components/input-number/");
        WebElement Element = webDriver.findElement(By.xpath("//input[@value='$ 1,000']"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", Element);

        WebElement inputNumber = webDriver.findElement(By.xpath("//input[@value='$ 1,000']"));
        inputNumber.clear();
        inputNumber.sendKeys("$ 2,000");
    }

    //ant - Transfer
    @Test
    public void testTransfer1() {
        webDriver.get("https://ant.design/components/transfer/");
        transfer("//section[@id='components-transfer-demo-basic']//div[@class='ant-transfer-list-body']//div[@class='LazyLoad is-visible']//li//span", "content2");
    }

    @Test
    public void testTransfer2() {
        webDriver.get("https://ant.design/components/transfer/");

        String s1 = "content2;content5";
        String[] temp = s1.split(";");
        for (String w : temp) {
            String optionTrans = String.format("//section[@id='components-transfer-demo-basic']//span[text()='%s']", w);
            WebElement optionXpath = webDriver.findElement(By.xpath(optionTrans));
            optionXpath.click();
        }
    }

    //ant - Timepicker
    @Test
    public void testTimepicker() {
        webDriver.get("https://ant.design/components/time-picker/");
        webDriver.findElement(By.xpath("//section[@id='components-time-picker-demo-basic']//input[@class='ant-time-picker-input']")).click();
        webDriver.findElement(By.xpath("//section[@id='components-time-picker-demo-basic']//input[@class='ant-time-picker-input']")).sendKeys("02:05:20");
    }

    //ant - Dropdown
    @Test
    public void testDropdown() throws InterruptedException {
        webDriver.get("https://ant.design/components/dropdown/");
        scrollToElement("//a[contains(.,'Hover me, Click menu item')]");

        Actions action = new Actions(webDriver);
        WebElement we = webDriver.findElement(By.xpath("//a[contains(.,'Hover me, Click menu item')]"));
        action.moveToElement(we).build().perform();

        webDriver.findElement(By.xpath("//li[contains(.,'1st menu item')]")).click();
    }

    //ant - Checkbox
    @Test
    public void testCheckbox1() {
        webDriver.get("https://ant.design/components/checkbox/");
        webDriver.findElement(By.xpath("//section[@id='components-checkbox-demo-basic']//input[@type='checkbox']")).click();
    }

    @Test
    public void testCheckbox2() {
        webDriver.get("https://ant.design/components/checkbox/");
        webDriver.findElement(By.xpath("//section[@id='components-checkbox-demo-controller']//input[@type='checkbox']")).click();
    }

    @Test
    public void testCheckbox3() {
        webDriver.get("https://ant.design/components/checkbox/");
        webDriver.findElement(By.xpath("//button[contains(.,'Uncheck')]")).click();
    }

    @Test
    public void testCheckbox4() {
        webDriver.get("https://ant.design/components/checkbox/");
        webDriver.findElement(By.xpath("//button[contains(.,'Disable')]")).click();
    }

    @Test
    public void testCheckbox5() {
        webDriver.get("https://ant.design/components/checkbox/");
        scrollToElement("//section[@id='components-checkbox-demo-check-all']//span[contains(.,'Check all')]");
        webDriver.findElement(By.xpath("//section[@id='components-checkbox-demo-check-all']//div[@class='ant-checkbox-group']//span[contains(.,'Apple')]")).click();
        webDriver.findElement(By.xpath("//section[@id='components-checkbox-demo-check-all']//div[@class='ant-checkbox-group']//span[contains(.,'Pear')]")).click();
    }

    //ant - Mentions
    @Test
    public void testMention() {
        webDriver.get("https://ant.design/components/mentions/");
        webDriver.findElement(By.xpath("//section[@id='components-mentions-demo-basic']//textarea")).sendKeys("@");
    }

    //ant - Rate
    @Test
    public void testRate() {
        webDriver.get("https://ant.design/components/rate/");
    }

    //ant - Radio
    @Test
    public void testRadio1() {
        webDriver.get("https://ant.design/components/radio/");
        webDriver.findElement(By.xpath("//section[@id='components-radio-demo-radiogroup']//section[@class='code-box-demo']//span[contains(.,'A')]")).click();
    }

    @Test
    public void testRadio2() {
        webDriver.get("https://ant.design/components/radio/");
        webDriver.findElement(By.xpath("//section[@id='components-radio-demo-radiogroup']//section[@class='code-box-demo']//span[contains(.,'A')]")).click();
        webDriver.findElement(By.xpath("//section[@id='components-radio-demo-radiogroup']//section[@class='code-box-demo']//span[contains(.,'B')]")).click();
    }

    //ant - Switch
    @Test
    public void testSwitch() {
        webDriver.get("https://ant.design/components/switch/");
        webDriver.findElement(By.xpath("//section[@id='components-switch-demo-basic']//button[@type='button']")).click();
        webDriver.findElement(By.xpath("//section[@id='components-switch-demo-basic']//button[@type='button']")).click();
    }

}

