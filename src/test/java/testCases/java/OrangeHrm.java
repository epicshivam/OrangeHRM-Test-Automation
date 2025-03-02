package testCases.java;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class OrangeHrm {

    public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    public WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @Test(priority = 1, enabled = false)
    public void loginInvalidTest() {
        driver.findElement(new By.ByXPath("//input[@placeholder='Username']")).sendKeys("Admi");

        driver.findElement(new By.ByXPath("//input[@placeholder='Password']")).sendKeys("admin123");

        WebElement element = driver.findElement(new By.ByXPath("//button[@type='submit']"));
        element.submit();

        String actual_message = driver
                .findElement(new By.ByXPath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();

        Assert.assertEquals(actual_message, "Invalid credentials");

    }

    @Test(priority = 2, enabled = false)
    public void loginTest() {
        driver.findElement(new By.ByXPath("//input[@placeholder='Username']")).sendKeys("Admin");

        driver.findElement(new By.ByXPath("//input[@placeholder='Password']")).sendKeys("admin123");

        WebElement element = driver.findElement(new By.ByXPath("//button[@type='submit']"));
        element.submit();

        String pageTitle = driver.getTitle();
        // if(pageTitle.equals("OrangeHRM")){
        // System.out.println("Login Successfull");
        // } else {
        // System.out.println("Login Failed");
        // }

        Assert.assertEquals(pageTitle, "OrangeHRM");

    }

    @Test(priority = 3, enabled = false)
    public void addEmployee() {
        driver.findElement(new By.ByXPath("//span[text()='PIM']")).click();
        driver.findElement(new By.ByXPath("//a[text()='Add Employee']")).click();
        driver.findElement(new By.ByXPath("//input[@placeholder='First Name']")).sendKeys("Shivam");
        driver.findElement(new By.ByXPath("//input[@placeholder='Last Name']")).sendKeys("Singh");
        driver.findElement(new By.ByXPath("//button[normalize-space()='Save']")).click();

        String actual_message = driver.findElement(new By.ByXPath("//h6[normalize-space()='Personal Details']"))
                .getText();

        Assert.assertEquals(actual_message, "Personal Details");
    }

    @Test(priority = 4, enabled = false)
    public void searchEmployeeById() throws InterruptedException {

        // login
        login();

        // searching employee
        String empId = "0412";
        driver.findElement(new By.ByXPath("//span[text()='PIM']")).click();
        driver.findElements(new By.ByTagName("input")).get(2).sendKeys(empId);
        driver.findElement(new By.ByXPath("//button[normalize-space()='Search']")).click();

        JavascriptExecutor driver1 = (JavascriptExecutor) driver;
        driver1.executeScript("window.scrollBy(0," + 500 + ")");

        Thread.sleep(5000);

        List<WebElement> row = driver.findElements(new By.ByXPath("//div[@role='row']"));

        String actual_message = "";

        if (row.size() > 1) {
            actual_message = driver.findElement(new By.ByXPath("((//div[@role='row'])[2]/div[@role='cell'])[2]"))
                    .getText();
        }

        Assert.assertEquals(actual_message, empId);
    }

    @Test(priority = 5, enabled = false)
    public void fileUpload() throws IOException, InterruptedException {

        // login
        login();

        // uploading file
        driver.findElement(new By.ByXPath("//span[text()='PIM']")).click();
        driver.findElement(new By.ByXPath("//span[@class='oxd-topbar-body-nav-tab-item']")).click();
        driver.findElement(new By.ByXPath("//a[normalize-space()='Data Import']")).click();
        driver.findElement(new By.ByXPath("//div[@class='oxd-file-button']")).click();

        Runtime.getRuntime().exec("C://Users//sshiv//IdeaProjects//leansele//UploadFileAutoScript.exe");

        Thread.sleep(5000);

        driver.findElement(new By.ByXPath("button[type='submit']")).click();

        logOut();

    }

    @Test(priority = 6, enabled = true)
    public void ListEmployees() throws InterruptedException {
        login();
        // find PIM Menu and click on PIM Menu
        driver.findElement(By.xpath("//span[text()='PIM']")).click();

        // Select Employee List
        driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
        Thread.sleep(3000);

        // find total links
        List<WebElement> totalLinksElements = driver.findElements(By.xpath("//ul[@class='oxd-pagination__ul']/li"));

        int totalLinks = totalLinksElements.size();

        for (int i = 0; i < totalLinks; i++)// 0,1,2,3,
        {

            try {
                String currentLinkText = totalLinksElements.get(i).getText();

                int page = Integer.parseInt(currentLinkText);
                System.out.println("Page: " + page);

                totalLinksElements.get(i).click();

                Thread.sleep(2000);

                List<WebElement> emp_list = driver.findElements(By.xpath("//div[@class='oxd-table-card']/div /div[4]"));

                for (int j = 0; j < emp_list.size(); j++) {
                    // print last name of each row
                    String lastName = emp_list.get(j).getText();
                    System.out.println(lastName);
                }
            } catch (Exception e) {
                System.out.println("Not a number.");
            }

        }

        Thread.sleep(5000);
        logOut();
    }

    @Test(priority = 7, enabled = false)
    public void deleteEmployee() throws InterruptedException {
        login();

        // find PIM Menu and click on PIM Menu
        driver.findElement(By.xpath("//span[text()='PIM']")).click();

        // Select Employee List
        driver.findElement(By.xpath("//a[text()='Employee List']")).click();

        // enter employee name
        driver.findElements(By.tagName("input")).get(1).sendKeys("Odis");

        // driver.findElement(By.tagName("input")).get(1).sendKeys("Nesta");

        // Click the search button.
        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

        Thread.sleep(3000);
        /////////////////// Delete/////////////////////////

        // click on delete icon of the record
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']")).click();

        // click on yes, delete messaage button
        driver.findElement(By.xpath(
                "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']"))
                .click();

        // check for message "No Record Found"
        String msg = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]")).getText();

        Assert.assertEquals(msg, "No Records Found");

        Thread.sleep(5000);
        logOut();

    }

    @Test(priority = 8, enabled = false)
    public void applyLeave() throws InterruptedException {
        // find username and enter username "Admin"
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

        // find password and enter password admin123
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

        // login button click
        driver.findElement(By.xpath("//button[@type='submit']")).submit();

        // click on leave menu
        driver.findElement(By.linkText("Leave")).click();

        // click on Apply menu
        driver.findElement(By.linkText("Apply")).click();

        // click on leave type drop down
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();

        // select CAN-FMLA option from leave type dropdown
        driver.findElement(By.xpath("//*[contains(text(),'CAN')]")).click();

        // enter from date
        driver.findElement(By.xpath("//div[@class='oxd-date-input']/input")).sendKeys("2024-08-04");

        // enter comment
        driver.findElement(By.tagName("textarea")).sendKeys("This is my personal leave");
        Thread.sleep(3000);

        // click on Apply button
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(5000);
        logOut();

    }

    public void login() {
        driver.findElement(new By.ByXPath("//input[@placeholder='Username']")).sendKeys("Admin");

        driver.findElement(new By.ByXPath("//input[@placeholder='Password']")).sendKeys("admin123");

        WebElement element = driver.findElement(new By.ByXPath("//button[@type='submit']"));
        element.submit();
    }

    public void logOut() {
        driver.findElement(new By.ByXPath("//p[@class='oxd-userdropdown-name']")).click();
        // driver.findElement(new
        // By.ByXPath("//a[normalize-space()='Logout']")).click();
        List<WebElement> webElementList = driver.findElements(new By.ByXPath("(//a[@class='oxd-userdropdown-link'])"));
        // System.out.println(webElementList.get(3).getText());
        webElementList.get(3).click();
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        logOut();
        Thread.sleep(1000);
        driver.close();
        driver.quit();
    }
}
