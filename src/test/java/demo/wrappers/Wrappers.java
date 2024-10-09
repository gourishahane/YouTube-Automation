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
        // Remove "views", "watching", or any other trailing text, and trim any whitespace
        viewText = viewText.replaceAll("[^0-9\\.MKBG]", "").trim();
    
        // If the last character is not a digit, it's a suffix (K, M, B)
        char suffix = viewText.charAt(viewText.length() - 1);
    
        // If the last character is a digit, no suffix is present
        if (Character.isDigit(suffix)) {
            return Double.parseDouble(viewText); // Just a plain number
        }
    
        // Extract the numerical part (e.g., "22" from "22M" or "1.2" from "1.2K")
        double number = Double.parseDouble(viewText.substring(0, viewText.length() - 1));
    
        // Multiply based on the suffix
        switch (suffix) {
            case 'M':
                return number * 1_000_000; // Millions
            case 'B':
                return number * 1_000_000_000; // Billions
            case 'K':
                return number * 1_000; // Thousands
            case 'G': // In case of billions marked as 'G' or something else
                return number * 1_000_000_000; // Treat G as Billion
            default:
                // If no valid suffix, just return the parsed number
                return number;
        }
    }
    
}
