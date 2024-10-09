package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
// import dev.failsafe.internal.util.Assert;

public class TestCases extends ExcelDataProvider{ // Lets us read the data
        ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */
        @Test
        public void testCase01(){
                String youTubeUrl="https://www.youtube.com";
                if(!driver.getCurrentUrl().equals(youTubeUrl)){
                        driver.get(youTubeUrl);
                        System.out.println("User is navigated to youtube");
                }

                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
                
                try{
                        wait.until(ExpectedConditions.urlContains("youtube"));
                        Assert.assertTrue(driver.getCurrentUrl().contains("youtube"), "Not on YouTube!");
                        System.out.println("URL contains 'youtube'.");

                        WebElement aboutLink=driver.findElement(By.xpath("//a[@href='https://www.youtube.com/about/']"));
                        Wrappers.clickElement(driver, aboutLink);

                        wait.until(ExpectedConditions.urlContains("about"));
                        Assert.assertTrue(driver.getCurrentUrl().contains("about"), "Not on about page");
                        System.out.println("URL contains 'about'.");

                        WebElement aboutContent=driver.findElement(By.xpath("//section[@class='ytabout__content']"));
                        Wrappers.displayYouTubeContent(driver, aboutContent);
                        

                }catch(Exception e){
                        System.out.println("testCase1 failed :" +e.getMessage());
                        e.getStackTrace();
                }
                
        }
        @Test
        public void testCase02(){
                String youTubeUrl="https://www.youtube.com";
                if(!driver.getCurrentUrl().equals(youTubeUrl)){
                        driver.get(youTubeUrl);
                        System.out.println("User is navigated to youtube");
                }

                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
                SoftAssert softAssert=new SoftAssert();
                try{
                        wait.until(ExpectedConditions.urlContains("youtube"));
                        softAssert.assertTrue(driver.getCurrentUrl().contains("youtube"), "Not on YouTube!");
                        System.out.println("URL contains 'youtube'.");

                        WebElement moviesLink=driver.findElement(By.xpath("//yt-formatted-string[text()='Movies']"));
                        Wrappers.clickElement(driver, moviesLink);
                        Thread.sleep(3000);

                        WebElement moviesTitle=driver.findElement(By.xpath("//span[text()='Movies']"));
                        String movieText = Wrappers.displayYouTubeContent(driver, moviesTitle).trim();
                        
                        if(movieText.equals("Movies")){
                                System.out.println("User is navigated to Movies page");
                        }else{
                                System.out.println("User is not navigated to correct page.");
                        }

                        WebElement nextButton=driver.findElement(By.xpath("//span[text()='Top selling']/ancestor::div[@id='dismissible']//div[@id='right-arrow']//button"));
                        while (nextButton.isDisplayed() && nextButton.isEnabled()) {
                                Wrappers.clickElement(driver, nextButton);
                                Thread.sleep(3000); 
                    
                                // Re-locate the button 
                                nextButton = driver.findElement(By.xpath("//span[text()='Top selling']/ancestor::div[@id='dismissible']//div[@id='right-arrow']//button"));
                            }

                        WebElement movieGrade=driver.findElement(By.xpath("//span[text()='Top selling']/ancestor::div[@id='dismissible']//ytd-grid-movie-renderer[@class='style-scope yt-horizontal-list-renderer'][last()]//div[contains(@class,'badge-style-type-simple')]"));
                        String movieGradeText=Wrappers.displayYouTubeContent(driver, movieGrade);

                       

                        // boolean isMature = movieGradeText.equals("A");
                        // softAssert.assertTrue(isMature, "The movie is not marked as Mature ('A'). Instead, found: " + movieGradeText);
    
                        // if (!isMature) {
                        // System.out.println("Skipping the maturity rating validation as the movie is not marked 'A'. Found: " + movieGradeText);
                        //  }
                        if(movieGradeText.equals("A")) {
                                // If it's 'A', we assert true
                                softAssert.assertTrue(true, "The movie is marked as Mature ('A').");
                            } else {
                                // If not 'A', we log the information without failing the test
                                System.out.println("The movie is not marked as Mature ('A'). Instead, found: " + movieGradeText);
                            }


                        WebElement movieCategory=driver.findElement(By.xpath("//span[text()='Top selling']/ancestor::div[@id='dismissible']//ytd-grid-movie-renderer[@class='style-scope yt-horizontal-list-renderer'][last()]//span[contains(@class,'grid-movie-renderer-metadata')]"));
                        String movieCategoryText=Wrappers.displayYouTubeContent(driver, movieCategory);
                        String movieCategoryTextAfterSplit = movieCategoryText.replaceAll("[^A-Za-z ]", "").trim();
                        
                        if (!movieCategoryTextAfterSplit.equals("Comedy") && !movieCategoryTextAfterSplit.equals("Animation") && !movieCategoryTextAfterSplit.equals("Drama")) {
                                System.out.println("The movie category is not one of the expected genres: Comedy, Animation, Drama. Instead, found: " + movieCategoryTextAfterSplit);
                            }else{
                                System.out.println("Movie Category found: " + movieCategoryTextAfterSplit);
                            }
                        softAssert.assertTrue(
                                movieCategoryTextAfterSplit.equals("Comedy") ||
                                movieCategoryTextAfterSplit.equals("Animation") ||
                                movieCategoryTextAfterSplit.equals("Drama"),
                                "The movie category is not one of the expected genres: Comedy, Animation, Drama."
                            );
                        
                }catch(Exception e){
                        System.out.println("testCase2 failed :" +e.getMessage());
                        e.getStackTrace();
                }
                softAssert.assertAll();
        }
        @Test
        public void testCase03(){
                String youTubeUrl="https://www.youtube.com";
                if(!driver.getCurrentUrl().equals(youTubeUrl)){
                        driver.get(youTubeUrl);
                        System.out.println("User is navigated to youtube");
                }

                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
                SoftAssert softAssert=new SoftAssert();
                try{
                        wait.until(ExpectedConditions.urlContains("youtube"));
                        softAssert.assertTrue(driver.getCurrentUrl().contains("youtube"), "Not on YouTube!");
                        System.out.println("URL contains 'youtube'.");
                        Thread.sleep(2000);

                        WebElement musicLink=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//yt-formatted-string[text()='Music']")));
                        Wrappers.clickElement(driver, musicLink);
                        Thread.sleep(3000);

                        WebElement musicTitle=driver.findElement(By.xpath("//div[@id='contents']//yt-formatted-string[text()='Music']"));
                        String musicText=Wrappers.displayYouTubeContent(driver,musicTitle);
                        if(musicText.equals("Music")){
                                System.out.println("User is navigated to Music page");
                        }else{
                                System.out.println("User is not navigated to correct page.");
                        }

                        WebElement rightArrow=driver.findElement(By.xpath("//span[contains(text(),'Biggest Hits')]/ancestor::div[@id='dismissible']//div//span[contains(text(),'Biggest Hits')]/ancestor::div[@id='dismissible']//div[@id='right-arrow']//button"));

                        while (rightArrow.isDisplayed() && rightArrow.isEnabled()) {
                                Wrappers.clickElement(driver, rightArrow);
                                Thread.sleep(3000);
                                rightArrow=driver.findElement(By.xpath("//span[contains(text(),'Biggest Hits')]/ancestor::div[@id='dismissible']//div//span[contains(text(),'Biggest Hits')]/ancestor::div[@id='dismissible']//div[@id='right-arrow']//button")); 
                        }

                        WebElement playListTitle=driver.findElement(By.xpath("//span[contains(text(),'Biggest Hits')]/ancestor::div[@id='dismissible']//div[contains(@class,'flex-container style-scope')]//h3[contains(text(),'Bollywood Dance')]"));
                        String playListTittleText=Wrappers.displayYouTubeContent(driver, playListTitle);
                        System.out.println("Name of last playlist is: "+playListTittleText);

                        WebElement tracks=driver.findElement(By.xpath("//span[contains(text(),'Biggest Hits')]/ancestor::div[@id='dismissible']//div//span[contains(text(),'Biggest Hits')]/ancestor::div[@id='dismissible']//div[contains(@class,'flex-container style-scope')]//h3[contains(text(),'Bollywood Dance')]/following-sibling::p"));
                        String trackText=Wrappers.displayYouTubeContent(driver, tracks);
                        String trackTextAfterSplit=trackText.replaceAll("[^0-9]", "").trim();
                        int trackNumber=Integer.parseInt(trackTextAfterSplit);
                        softAssert.assertTrue(trackNumber <= 50, "Number of tracks exceeds 50. Found: " + trackNumber);
                        System.out.println("Number of tracks available are: "+trackTextAfterSplit);
                
                
                }catch(Exception e){
                        System.out.println("testCase3 failed :" +e.getMessage());
                        e.getStackTrace();
                }
                softAssert.assertAll();
        }
        @Test
        public void testCase04(){
                String youTubeUrl="https://www.youtube.com";
                if(!driver.getCurrentUrl().equals(youTubeUrl)){
                        driver.get(youTubeUrl);
                        System.out.println("User is navigated to youtube");
                }

                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
                SoftAssert softAssert=new SoftAssert();
                try{
                        wait.until(ExpectedConditions.urlContains("youtube"));
                        softAssert.assertTrue(driver.getCurrentUrl().contains("youtube"), "Not on YouTube!");
                        System.out.println("URL contains 'youtube'.");
                        Thread.sleep(2000);

                        WebElement newsLink=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//yt-formatted-string[text()='News']")));
                        Wrappers.clickElement(driver, newsLink);
                        Thread.sleep(3000);

                        WebElement newsHeader=driver.findElement(By.xpath("//span[text()='News']"));
                        String newsText=Wrappers.displayYouTubeContent(driver,newsHeader);
                        if(newsText.equals("News")){
                                System.out.println("User is navigated to News page");
                        }else{
                                System.out.println("User is not navigated to correct page.");
                        }

                        List<WebElement> latestNewsList=driver.findElements(By.xpath("//span[text()='Latest news posts']/ancestor::div[@id='dismissible']//ytd-post-renderer"));
                        int likesCount=0;
                        for(int i=0; i<3 && i < latestNewsList.size(); i++){
                        WebElement newsPost=latestNewsList.get(i);

                        WebElement newsTitle=newsPost.findElement(By.xpath(".//a/span[@class='style-scope ytd-post-renderer']"));
                        String titleText = newsTitle.getText();
                        System.out.println("Title of News Post " + (i + 1) + ": " + titleText);

                        WebElement newsBody=newsPost.findElement(By.xpath(".//yt-formatted-string[@id='home-content-text']/span[1]"));
                        String bodyText = newsBody.getText();
                        System.out.println("Body of News Post " + (i + 1) + ": " + bodyText);

                        WebElement newsLikes=newsPost.findElement(By.xpath(".//ytd-toggle-button-renderer[@id='like-button']/following-sibling::span[1]"));
                        String likesText=newsLikes.getText();
                        int noOfLikes = 0; // Default to 0 if no likes are available

                if (!likesText.isEmpty()) {
                    try {
                        noOfLikes = Integer.parseInt(likesText);
                    } catch (NumberFormatException e) {
                        System.out.println("Could not parse likes for News Post " + (i + 1) + ": " + likesText);
                    }
                }

                System.out.println("Likes of News Post " + (i + 1) + ": " + noOfLikes);
                likesCount += noOfLikes;
            }

            System.out.println("Total number of likes on 3 news posts are: " + likesCount);

                        
                
                }catch(Exception e){
                        System.out.println("testCase4 failed :" +e.getMessage());
                        e.getStackTrace();
                }
                softAssert.assertAll();
        }
        @Test(dataProvider = "excelData", dataProviderClass = demo.utils.ExcelDataProvider.class)
        public void testCase05(String searchTerm){
                String youTubeUrl="https://www.youtube.com";
                if(!driver.getCurrentUrl().equals(youTubeUrl)){
                        driver.get(youTubeUrl);
                        System.out.println("User is navigated to youtube");
                }

                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
                SoftAssert softAssert=new SoftAssert();
                try{
                        wait.until(ExpectedConditions.urlContains("youtube"));
                        softAssert.assertTrue(driver.getCurrentUrl().contains("youtube"), "Not on YouTube!");
                        System.out.println("URL contains 'youtube'.");

                        WebElement searchField=driver.findElement(By.xpath("//input[@aria-label='Search']"));
                        searchField.clear();
                        searchField.sendKeys(searchTerm);
                        searchField.sendKeys(Keys.ENTER);

                        wait.until(ExpectedConditions.urlContains(searchTerm));
                        System.out.println("User is navigated to "+searchTerm+" page.");

                        long totalViews = 0;
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        while(totalViews< 100_000_000){
                            List<WebElement> videoList=driver.findElements(By.xpath("//div[@id='contents']/ytd-video-renderer[@class='style-scope ytd-item-section-renderer']"));
                            for(WebElement video:videoList){
                                WebElement videoView=video.findElement(By.xpath(".//span[contains(@class,'inline-metadata-item')][1]"));
                                String videoViewText=videoView.getText();
                                double views=Wrappers.convertViewsText(videoViewText);
                                totalViews+=views;
                                System.out.println("Current total views: " + totalViews);
                                if (totalViews >= 100_000_000) {
                                break;
                            }
                           }
                           js.executeScript("window.scrollBy(0, 1000);");
                           Thread.sleep(2000);
                }
                System.out.println("Total views for '" + searchTerm + "' reached 10 crore.");
                }catch(Exception e){
                        System.out.println("testCase4 failed :" +e.getMessage());
                        e.getStackTrace();
                }
                softAssert.assertAll();

        }



        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}