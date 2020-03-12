package stepFiles;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.*;
import java.awt.event.InputEvent;
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
    public void search4events() {
        assertThat(getUrl().contains("https://dou.ua/calendar/tags/QA/%D0%94%D0%BD%D0%B5%D0%BF%D1%80/"));
        List<WebElement> events = WebDriverHolder.getDriver().findElements(By.cssSelector("h2.title"));
        System.out.println("all events found: " + events.size());
        List<WebElement> evLinks = WebDriverHolder.getDriver().findElements(By.cssSelector("h2.title a"));

        //getting names of the events and grouping those by relation to automation
        for (WebElement e : events) {
            String name = e.getText();

            if (name.contains("utomation")) {
                events.forEach(event -> {
                    System.out.println("found: " + e.getText());
                    String link = WebDriverHolder.getDriver().findElement(By.cssSelector("h2.title a")).getAttribute("href");
                    System.out.println("link: "+link);
                });
            }
        }
        try {
            Thread.sleep(4000);
        }
        catch (Exception ex){
            System.out.println(ex);
        }


//        for (WebElement e : events) {
//            String name = e.getText();
//            if (name.contains("utomation")) {
//                for (WebElement l : evLinks) {
//                    // getting links of the events
//                    String link = l.getAttribute("href");
//                    System.out.println("automation related events: " + link);
//                    break;
//                }
//
//                if (!name.contains("utomation")) {
//                    System.out.println("other events: " + name);
//                    break;
//                }
//            }
//        }
    }

    @After
    public void quit(){
        WebDriverHolder.quitDriver();
    }
}

