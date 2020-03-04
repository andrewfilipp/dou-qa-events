package stepFiles;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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

        WebElement tagFilter = WebDriverHolder.getDriver().findElement(By.xpath("//select[@name='tag']"));
        tagFilter.click();
        List<WebElement> tagOption = WebDriverHolder.getDriver().findElements(By.xpath(".//option"));
        WebElement QA = WebDriverHolder.getDriver().findElement(By.xpath(".//option[contains(text(),\"QA\")]"));
        if (tagOption.contains(QA)) {
            QA.click();
        }
        try{
            sleep(2000);
        }
        catch (Exception e){
        }

    }

    @Then("search4events")
    public void search4events(){

        this.getUrl();
        if (getUrl().equals("https://dou.ua/calendar/tags/QA/%D0%94%D0%BD%D0%B5%D0%BF%D1%80/")){
            List <WebElement> events = WebDriverHolder.getDriver().findElements(By.cssSelector("h2.title"));
            System.out.println("all events found: "+events.size());
            for (WebElement e : events){
                String name = e.getText();
                while (name.contains("utomation")){
                    System.out.println("automation related events: "+name);
                    break;
                }
                while (!name.contains("utomation")){
                    System.out.println("other events: "+name);
                    break;
                }

            }
        }
        else {
            System.out.println(this.getUrl());
        }


    }



    @After
    public void quit(){
        WebDriverHolder.quitDriver();
    }
}
