package stepFiles;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;
import static stepFiles.WebDriverHolder.getDriver;


public class Searching {

    public String getUrl(){
        String CurrentUrl = WebDriverHolder.getDriver().getCurrentUrl();
        return CurrentUrl;
    }

    @Given("openDou")
    public void openDou(){
        //get to the dou page
        WebDriverHolder.getDriver().get("https://dou.ua");
        String ExpectedUrl = "https://dou.ua";
        System.out.println("at the homepage: "+this.getUrl());
        Assert.assertTrue ("URLs do not match", this.getUrl().contains(ExpectedUrl));

    }

    @When("selectCalendar")
    public void selectCalendar(){
        WebElement calendar = WebDriverHolder.getDriver().findElement(By.xpath("//a[@href=\"https://dou.ua/calendar/\"]"));
        calendar.click();
        System.out.println("at the calendar page: "+this.getUrl());

        try{
            sleep(2000);
        }
        catch (Exception e){
        }
        WebElement cityFilter = WebDriverHolder.getDriver().findElement(By.xpath("//select[@name='city']"));
        cityFilter.click();
        List<WebElement> cityOption = WebDriverHolder.getDriver().findElements(By.xpath(".//option"));
        WebElement dnepr = WebDriverHolder.getDriver().findElement(By.xpath(".//option[contains(text(),\"Днепр\")]"));


        if (cityOption.contains(dnepr)) {
            dnepr.click();

        }

        try{
            sleep(2000);
        }
        catch (Exception e){
        }



    }




    @After
    public void quit(){
        WebDriverHolder.quitDriver();
    }
}
