package com.healenium_mcp.healenium_mcp_server;


import com.epam.healenium.SelfHealingDriverWait;
import org.openqa.selenium.*;
import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class HealeniumToolService {
    private SelfHealingDriver driver;
    private WebDriverWait wait;
    private Action action;
    public WebElement locateElement(String type, String value){
        try{
            switch (type.toLowerCase()){
                case "id"->{
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value)));
                }
                case "classname"->{
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(value)));
                }
                case "tagname"->{
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(value)));
                }
                case "name"->{
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(value)));
                }
                case "link text"->{
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(value)));
                }
                case "partial link text"->{
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(value)));
                }
                case "css selector"->{
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(value)));
                }
                case "xpath"->{
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
                }
                case null,default ->{
                    throw  new NoSuchElementException("No such element Found");
                }
            }
        } catch (RuntimeException e) {
            throw new NoSuchElementException("Exception thrown for element not found:"+e);
        }
    }
    public List<WebElement> locateElements(String type, String value){
        try{
            switch (type.toLowerCase()){
                case "id"->{
                    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(value)));
                }
                case "classname"->{
                    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(value)));
                }
                case "tagname"->{
                    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(value)));
                }
                case "name"->{
                    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(value)));
                }
                case "link text"->{
                    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(value)));
                }
                case "partial link text"->{
                    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(value)));
                }
                case "css selector"->{
                    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(value)));
                }
                case "xpath"->{
                    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(value)));
                }
                case null,default ->{
                    throw  new NoSuchElementException("No such element Found");
                }
            }
        } catch (RuntimeException e) {
            throw new NoSuchElementException("Exception thrown for element not found:"+e);
        }
    }
    public HealeniumToolService(){}
    @Tool(description = "Starts A Browser Session")
    public void startBrowser(String browser) {
        WebDriver delegate;
        switch (browser.toLowerCase()) {
            case "chrome"->{
                delegate = new ChromeDriver();
            }
            case "edge"->{
                delegate =new EdgeDriver();
            }
            case "firefox"->{
                delegate =new FirefoxDriver();
            }
            default -> {
                delegate=new ChromeDriver();
            }
        }
       this.driver= SelfHealingDriver.create(delegate);
        this.wait=new SelfHealingDriverWait(driver, Duration.ofSeconds(20));
    }
    @Tool(description = "Navigate To A Url")
    public void navigateTo(String url){
        driver.get(url);
    }
    @Tool(description = "Find Element with Locator")
    public String findElement(String type, String value){
        try{
            locateElement(type, value);
            return "Element Found";
        } catch (RuntimeException e) {
            throw new NoSuchElementException("Exception thrown for element not found:"+e);
        }
    }
    @Tool(description = "Click a found WebElement")
    public String clickElement(String type,String value){
        try{
            WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locateElement(type, value)));
            element.click();
            return "Element Clicked";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Tool(description = "Finding Multiple Web Elements")
    public String findElements(String type,String value){
        try {
            locateElements(type,value);
            return "All Elements Found";
        } catch (RuntimeException e) {
            throw new NoSuchElementException("Exception thrown for element not found:"+e);
        }
    }
    @Tool(description = "Click all elements")
    public String clickElements(String type,String value){
        try{
            List<WebElement> elements= locateElements(type, value);
            for (WebElement e:elements){
                e.click();
            }
            return "All Elements Clicked";
        } catch (RuntimeException e) {
            throw new NoSuchElementException("Exception thrown for element not found:"+e);
        }
    }
    @Tool(description = "Hover Over A Element")
    public String hoverOverElement(String type,String value){
        try{
            WebElement element=locateElement(type, value);
            action= new Actions(driver).moveToElement(element).build();
            action.perform();
            return "Successfully Hovered";
        } catch (NoSuchElementException e){
            throw new NoSuchElementException("No Such Element Found in the Website:"+e);
        }
    }
    @Tool(description = "Hover Over Multiple Elements")
    public String hoverOverAll(String type,String value){
        try{
            List<WebElement> elements=locateElements(type, value);
            Actions actions=new Actions(driver);
            for(WebElement e:elements){
                actions.moveToElement(e);
            }
            action=actions.build();
            action.perform();
            return "Successfully Hovered Over All";
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No Such element Present:"+e);
        }
    }
    @Tool(description = "Send Keys to An Element")
    public String sendKeysToElement(String type,String value,String keys){
        try{
            WebElement element=locateElement(type, value);
            element.sendKeys(keys);
            return "Keys Sent Successfully";
        } catch (NoSuchElementException e){
            throw new NoSuchElementException("No Such Text Box:"+e);
        } catch (ElementNotInteractableException e){
            throw new ElementNotInteractableException("This element is not enabled:"+e);
        }
    }
    @Tool(description = "Get Current Url")
    public String getUrl(){
        return driver.getCurrentUrl();
    }
    @Tool(description = "Get Page Title")
    public String getTitle(){
        return driver.getTitle();
    }
    @Tool(description = "Maximize Window")
    public String maximizeWindow(){
        driver.manage().window().maximize();
        return "Window Maximized";
    }
    @Tool(description = "Minimize Window")
    public String minimizeWindow(){
        driver.manage().window().minimize();
        return "Window Minimized";
    }
    @Tool(description = "Resize Window")
    public String resizeWindow(int width,int height){
        Dimension dimension=new Dimension(width, height);
        driver.manage().window().setSize(dimension);
        return "Window Resized";
    }
    @Tool(description = "Closes All Browser Sessions")
    public void closeBrowser() {
        driver.quit();
    }
}
