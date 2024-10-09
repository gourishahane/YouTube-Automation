package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void clickElement(ChromeDriver driver,WebElement element){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            System.out.println("User clicked on element");

        }catch(Exception e){
            System.out.println("Exception occurred while clicking on element :"+e.getMessage());
            e.getStackTrace();
        }
    }
    public static String displayYouTubeContent(ChromeDriver driver,WebElement element){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        String elemetText=null;
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
            if(element.isDisplayed()){
                elemetText=element.getText();
                System.out.println("Text is displayed as :"+elemetText);
            }
          

        }catch(Exception e){
            System.out.println("Exception occurred in displaying youtube content :"+e.getMessage());
            e.getStackTrace();
        }
        return elemetText;
    }
    public static double convertViewsText(String viewText) {
        // Remove the word "views" and trim any whitespace
        viewText = viewText.replace("views", "").trim();

        // Check the last character to determine the multiplier
        char suffix = viewText.charAt(viewText.length() - 1);

        // Extract the numerical part
        double number = Double.parseDouble(viewText.substring(0, viewText.length() - 1));

        // Multiply based on suffix
        switch (suffix) {
            case 'M':
                return number * 1_000_000; // Millions
            case 'B':
                return number * 1_000_000_000; // Billions
            case 'K':
                return number * 1_000; // Thousands
            default:
                // If no suffix, it's just a raw number (e.g., "500 views")
                return Double.parseDouble(viewText);
        }
    }
}
