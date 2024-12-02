## Automated YouTube Search and Analysis

## Project Title
Automating YouTube Search and Analysis Using Selenium

## Description
This project automates YouTube search and analysis workflows using Selenium WebDriver. It handles various functionalities such as searching for videos, navigating through tabs (Movies, Music, News), verifying specific elements, and extracting relevant data like views, likes, and video details for further analysis.

## Key Features
- Navigation: Automatically navigates to YouTube's homepage and handles page loads.
- Search Functionality: Dynamically searches for specified content such as Movies, Music, or trending videos.
- Tab Navigation: Navigates through specific YouTube tabs like "Movies," "Music," and "News" for targeted searches.
- Data Extraction: Extracts and processes key data, such as:
 1. Video titles, views, likes, and comments.
 2. Video category and type (e.g., Music, Movies).
 3. Video metadata like tags, duration, and ratings.
- Dynamic Sorting and Filtering: Automates the sorting of videos based on views, likes, or other criteria.
- Validation: Ensures extracted data meets specified criteria and performs analysis based on user requirements.
  
## Installation
1. Clone the Repository:
git clone https://github.com/gourishahane/YouTube-Automation.git

2. Set Up the Environment:
- Install Java 11 or higher.
- Download and configure ChromeDriver compatible with your browser version.
- Install Gradle or Maven for dependency management.
- Add the required dependencies for Selenium WebDriver and TestNG in the build configuration file (build.gradle or pom.xml).

3. Navigate to the Project Directory:
cd ME_QA_XYOUTUBE_MODULE_L1

4. Build the Project:
gradle build

5. Set Up Browser Drivers: Ensure the required browser driver (e.g., ChromeDriver) is installed and available in the system path.

6. Execute Tests:
gradle test

## Usage
1. Run Test Cases: Execute specific TestNG test classes to automate different workflows:

TestCase01: Searches for trending videos and extracts video details such as title, views, and likes.
TestCase02: Navigates to the "Movies" tab, verifies video category, and extracts data such as likes and views for top videos.
TestCase03: Navigates to the "Music" tab, filters videos by views, and checks video metadata (e.g., duration, genre).
TestCase04: Extracts specific video details from the search result, such as the video’s URL and total number of views.

2. Key Functions:

- Wrappers: Reusable methods for searching videos, applying filters, scrolling pages, and extracting data.
- Dynamic Filtering: Filters videos based on specific attributes like views, category, or upload date.
- Validation: Confirms that video details match expected criteria and logs the results.
  
## Output
Console Logs: Detailed console logs for each test case, including extracted video titles, views, likes, and other metadata.
Data Storage: Option to save extracted data into files (e.g., CSV, JSON) for offline analysis.

## Dependencies
Selenium WebDriver: For browser automation.
TestNG: For test case execution and assertions.
Gradle: As the build and dependency management tool.

## Contact Information
Name: Gouri Shahane
Email: gouri.shahane@example.com
GitHub: github.com/gourishahane
LinkedIn: linkedin.com/in/gourishahane
