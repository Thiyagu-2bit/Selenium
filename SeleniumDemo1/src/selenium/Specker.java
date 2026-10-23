package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class Specker {

    public static void main(String[] args) throws InterruptedException {

        // =====================================================
        // WEBSITE URLS
        // =====================================================

        String website1 =
                "https://theforexexpo.com/dubai/speakers";

        String website2 =
                "https://connect.hqmena.com/event/forex-expo-dubai-2026/people/RXZlbnRWaWV3XzE5NDM3MzA=";


        // =====================================================
        // CHROME SETUP
        // =====================================================

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");

        WebDriver driver =
                new ChromeDriver(options);

        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));


        try {

            // =====================================================
            // WEBSITE 1
            // =====================================================

            System.out.println();
            System.out.println("==========================================");
            System.out.println("       WEBSITE 1 - SPEAKER CHECK");
            System.out.println("==========================================");

            driver.get(website1);

            Thread.sleep(5000);

            // Scroll to load all speakers
            scrollPage(driver);

            Thread.sleep(3000);


            Set<String> website1Names =
                    getWebsite1Speakers(driver);


            System.out.println();
            System.out.println(
                    "Website 1 Total Speakers : "
                    + website1Names.size()
            );


            // =====================================================
            // SHOW WEBSITE 1 NAMES
            // =====================================================

            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println("WEBSITE 1 SPEAKER NAMES");
            System.out.println("------------------------------------------");


            for (String speaker : website1Names) {

                System.out.println(speaker);
            }


            // =====================================================
            // WEBSITE 1 VALIDATION
            // =====================================================

            if (website1Names.isEmpty()) {

                System.out.println();
                System.out.println(
                        "ERROR: Website 1 speakers were not found."
                );

                return;
            }


            // =====================================================
            // WEBSITE 2 - HQMENA
            // =====================================================

            System.out.println();
            System.out.println("==========================================");
            System.out.println("       WEBSITE 2 - HQMENA");
            System.out.println("==========================================");

            driver.get(website2);

            Thread.sleep(7000);


            // =====================================================
            // MANUAL LOGIN
            // =====================================================

            System.out.println();
            System.out.println("==========================================");
            System.out.println("          HQMENA LOGIN REQUIRED");
            System.out.println("==========================================");

            System.out.println(
                    "Please login/join the HQMENA event in Chrome."
            );

            System.out.println(
                    "After login, open the PEOPLE/SPEAKERS page."
            );

            System.out.println(
                    "Selenium will wait for 60 seconds."
            );

            System.out.println(
                    "==========================================");


            // Wait 60 seconds for manual login
            Thread.sleep(60000);


            // =====================================================
            // SCROLL HQMENA PAGE
            // =====================================================

            scrollPage(driver);

            Thread.sleep(5000);


            // =====================================================
            // GET WEBSITE 2 SPEAKERS
            // =====================================================

            Set<String> website2Names =
                    getWebsite2Speakers(driver);


            System.out.println();
            System.out.println(
                    "Website 2 Total Speakers : "
                    + website2Names.size()
            );


            // =====================================================
            // SHOW WEBSITE 2 NAMES
            // =====================================================

            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println("WEBSITE 2 SPEAKER NAMES");
            System.out.println("------------------------------------------");


            for (String speaker : website2Names) {

                System.out.println(speaker);
            }


            // =====================================================
            // WEBSITE 2 VALIDATION
            // =====================================================

            if (website2Names.isEmpty()) {

                System.out.println();
                System.out.println("==========================================");
                System.out.println("       WEBSITE 2 DATA NOT FOUND");
                System.out.println("==========================================");

                System.out.println(
                        "HQMENA speaker/people names were not found."
                );

                System.out.println(
                        "Make sure you are logged in and the"
                );

                System.out.println(
                        "People/Speakers list is visible."
                );

                System.out.println();
                System.out.println(
                        "COMPARISON STOPPED."
                );

                return;
            }


            // =====================================================
            // COMPARE
            // =====================================================

            compareSpeakers(
                    website1Names,
                    website2Names
            );


        } finally {

            driver.quit();
        }
    }


    // =============================================================
    // WEBSITE 1 SPEAKERS
    // =============================================================

    private static Set<String> getWebsite1Speakers(
            WebDriver driver) {


        Set<String> names =
                new TreeSet<>();


        // ---------------------------------------------------------
        // Try H3
        // ---------------------------------------------------------

        List<WebElement> elements =
                driver.findElements(
                        By.cssSelector("h3")
                );


        System.out.println(
                "Website 1 h3 elements found : "
                + elements.size()
        );


        for (WebElement element : elements) {

            String name =
                    element.getText().trim();


            if (isValidSpeakerName(name)) {

                names.add(
                        normalize(name)
                );
            }
        }


        // ---------------------------------------------------------
        // Try H2 if necessary
        // ---------------------------------------------------------

        if (names.isEmpty()) {

            elements =
                    driver.findElements(
                            By.cssSelector("h2")
                    );


            System.out.println(
                    "Website 1 h2 elements found : "
                    + elements.size()
            );


            for (WebElement element : elements) {

                String name =
                        element.getText().trim();


                if (isValidSpeakerName(name)) {

                    names.add(
                            normalize(name)
                    );
                }
            }
        }


        return names;
    }


    // =============================================================
    // WEBSITE 2 SPEAKERS
    // =============================================================

    private static Set<String> getWebsite2Speakers(
            WebDriver driver) {


        Set<String> names =
                new TreeSet<>();


        // ---------------------------------------------------------
        // METHOD 1
        // Look for /people/ links
        // ---------------------------------------------------------

        List<WebElement> elements =
                driver.findElements(
                        By.xpath(
                                "//a[contains(@href,'/people/')]"
                        )
                );


        System.out.println(
                "HQMENA people links found : "
                + elements.size()
        );


        for (WebElement element : elements) {

            String name =
                    element.getText().trim();


            if (isValidSpeakerName(name)) {

                names.add(
                        normalize(name)
                );
            }
        }


        // ---------------------------------------------------------
        // METHOD 2
        // H3
        // ---------------------------------------------------------

        if (names.isEmpty()) {

            elements =
                    driver.findElements(
                            By.cssSelector("h3")
                    );


            System.out.println(
                    "HQMENA h3 elements found : "
                    + elements.size()
            );


            for (WebElement element : elements) {

                String name =
                        element.getText().trim();


                if (isValidSpeakerName(name)) {

                    names.add(
                            normalize(name)
                    );
                }
            }
        }


        // ---------------------------------------------------------
        // METHOD 3
        // H2
        // ---------------------------------------------------------

        if (names.isEmpty()) {

            elements =
                    driver.findElements(
                            By.cssSelector("h2")
                    );


            System.out.println(
                    "HQMENA h2 elements found : "
                    + elements.size()
            );


            for (WebElement element : elements) {

                String name =
                        element.getText().trim();


                if (isValidSpeakerName(name)) {

                    names.add(
                            normalize(name)
                    );
                }
            }
        }


        // ---------------------------------------------------------
        // METHOD 4
        // H4
        // ---------------------------------------------------------

        if (names.isEmpty()) {

            elements =
                    driver.findElements(
                            By.cssSelector("h4")
                    );


            System.out.println(
                    "HQMENA h4 elements found : "
                    + elements.size()
            );


            for (WebElement element : elements) {

                String name =
                        element.getText().trim();


                if (isValidSpeakerName(name)) {

                    names.add(
                            normalize(name)
                    );
                }
            }
        }


        // ---------------------------------------------------------
        // METHOD 5
        // Elements with name-related class
        // ---------------------------------------------------------

        if (names.isEmpty()) {

            elements =
                    driver.findElements(
                            By.xpath(
                                "//*[contains(translate(@class,"
                                + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
                                + "'abcdefghijklmnopqrstuvwxyz'),"
                                + "'name')]"
                            )
                    );


            System.out.println(
                    "HQMENA name-class elements found : "
                    + elements.size()
            );


            for (WebElement element : elements) {

                String name =
                        element.getText().trim();


                if (isValidSpeakerName(name)) {

                    names.add(
                            normalize(name)
                    );
                }
            }
        }


        return names;
    }


    // =============================================================
    // COMPARE SPEAKERS
    // =============================================================

    private static void compareSpeakers(
            Set<String> website1Names,
            Set<String> website2Names) {


        // =====================================================
        // MATCHED
        // =====================================================

        Set<String> matchedSpeakers =
                new TreeSet<>(
                        website1Names
                );


        matchedSpeakers.retainAll(
                website2Names
        );


        // =====================================================
        // MISSING FROM WEBSITE 1
        //
        // Exists in Website 2
        // but not Website 1
        // =====================================================

        Set<String> missingFromWebsite1 =
                new TreeSet<>(
                        website2Names
                );


        missingFromWebsite1.removeAll(
                website1Names
        );


        // =====================================================
        // MISSING FROM WEBSITE 2
        //
        // Exists in Website 1
        // but not Website 2
        // =====================================================

        Set<String> missingFromWebsite2 =
                new TreeSet<>(
                        website1Names
                );


        missingFromWebsite2.removeAll(
                website2Names
        );


        // =====================================================
        // TOTAL COUNT
        // =====================================================

        System.out.println();
        System.out.println("==========================================");
        System.out.println("          TOTAL SPEAKER COUNT");
        System.out.println("==========================================");


        System.out.println(
                "Website 1 Total Speakers : "
                + website1Names.size()
        );


        System.out.println(
                "Website 2 Total Speakers : "
                + website2Names.size()
        );


        System.out.println(
                "Matched Speakers          : "
                + matchedSpeakers.size()
        );


        System.out.println(
                "Missing from Website 1   : "
                + missingFromWebsite1.size()
        );


        System.out.println(
                "Missing from Website 2   : "
                + missingFromWebsite2.size()
        );


        // =====================================================
        // MATCHED SPEAKERS
        // =====================================================

        System.out.println();
        System.out.println("==========================================");
        System.out.println("           MATCHED SPEAKERS");
        System.out.println("==========================================");


        if (matchedSpeakers.isEmpty()) {

            System.out.println(
                    "No matched speakers."
            );

        } else {

            int number = 1;

            for (String speaker :
                    matchedSpeakers) {

                System.out.println(
                        number
                        + ". "
                        + speaker
                        + " - MATCH"
                );

                number++;
            }
        }


        // =====================================================
        // MISSING FROM WEBSITE 1
        // =====================================================

        System.out.println();
        System.out.println("==========================================");
        System.out.println("       MISSING FROM WEBSITE 1");
        System.out.println("==========================================");


        if (missingFromWebsite1.isEmpty()) {

            System.out.println(
                    "None"
            );

        } else {

            int number = 1;

            for (String speaker :
                    missingFromWebsite1) {

                System.out.println(
                        number
                        + ". "
                        + speaker
                        + " - MISSING IN WEBSITE 1"
                );

                number++;
            }
        }


        // =====================================================
        // MISSING FROM WEBSITE 2
        // =====================================================

        System.out.println();
        System.out.println("==========================================");
        System.out.println("       MISSING FROM WEBSITE 2");
        System.out.println("==========================================");


        if (missingFromWebsite2.isEmpty()) {

            System.out.println(
                    "None"
            );

        } else {

            int number = 1;

            for (String speaker :
                    missingFromWebsite2) {

                System.out.println(
                        number
                        + ". "
                        + speaker
                        + " - MISSING IN WEBSITE 2"
                );

                number++;
            }
        }


        // =====================================================
        // FINAL RESULT
        // =====================================================

        System.out.println();
        System.out.println("==========================================");
        System.out.println("             FINAL RESULT");
        System.out.println("==========================================");


        if (missingFromWebsite1.isEmpty()
                && missingFromWebsite2.isEmpty()) {

            System.out.println();
            System.out.println(
                    "ALL SPEAKERS MATCHED"
            );

            System.out.println(
                    "NO MISMATCH FOUND"
            );

        } else {

            System.out.println();
            System.out.println(
                    "MISMATCH FOUND"
            );

            System.out.println(
                    "Missing from Website 1 : "
                    + missingFromWebsite1.size()
            );

            System.out.println(
                    "Missing from Website 2 : "
                    + missingFromWebsite2.size()
            );
        }


        // =====================================================
        // FINAL SUMMARY
        // =====================================================

        System.out.println();
        System.out.println("==========================================");
        System.out.println("              SUMMARY");
        System.out.println("==========================================");

        System.out.println(
                "Website 1 : "
                + website1Names.size()
        );

        System.out.println(
                "Website 2 : "
                + website2Names.size()
        );

        System.out.println(
                "Matched   : "
                + matchedSpeakers.size()
        );

        System.out.println(
                "Site 1 Missing : "
                + missingFromWebsite1.size()
        );

        System.out.println(
                "Site 2 Missing : "
                + missingFromWebsite2.size()
        );
    }


    // =============================================================
    // SCROLL PAGE
    // =============================================================

    private static void scrollPage(
            WebDriver driver)
            throws InterruptedException {


        JavascriptExecutor js =
                (JavascriptExecutor) driver;


        long lastHeight =
                ((Number) js.executeScript(
                        "return document.body.scrollHeight"
                )).longValue();


        for (int i = 0; i < 20; i++) {


            js.executeScript(
                    "window.scrollTo(0, document.body.scrollHeight);"
            );


            Thread.sleep(1000);


            long newHeight =
                    ((Number) js.executeScript(
                            "return document.body.scrollHeight"
                    )).longValue();


            if (newHeight == lastHeight) {

                break;
            }


            lastHeight =
                    newHeight;
        }


        // Return to top

        js.executeScript(
                "window.scrollTo(0, 0);"
        );
    }


    // =============================================================
    // VALID SPEAKER NAME
    // =============================================================

    private static boolean isValidSpeakerName(
            String name) {


        if (name == null) {

            return false;
        }


        name =
                name.trim();


        if (name.isEmpty()) {

            return false;
        }


        // Ignore very long text blocks

        if (name.length() > 100) {

            return false;
        }


        String lower =
                name.toLowerCase();


        // Ignore website headings/buttons

        if (lower.equals("speaker")
                || lower.equals("speakers")
                || lower.equals("home")
                || lower.equals("agenda")
                || lower.equals("sponsors")
                || lower.equals("partners")
                || lower.equals("floor plan")
                || lower.equals("contact us")
                || lower.equals("login")
                || lower.equals("log in")
                || lower.equals("join")
                || lower.equals("join the event")
                || lower.equals("register")
                || lower.equals("registration")
                || lower.equals("continue")
                || lower.equals("search")
                || lower.equals("people")) {

            return false;
        }


        // Ignore obvious button text

        if (lower.contains("see all")
                || lower.contains("read more")
                || lower.contains("learn more")
                || lower.contains("view profile")) {

            return false;
        }


        return true;
    }


    // =============================================================
    // NORMALIZE NAME
    // =============================================================

    private static String normalize(
            String name) {


        return name

                // Lowercase
                .toLowerCase()

                // Replace pipe
                .replaceAll("\\|", " ")

                // Remove special characters
                .replaceAll("[^a-z0-9 ]", "")

                // Remove multiple spaces
                .replaceAll("\\s+", " ")

                // Remove beginning/end spaces
                .trim();
    }
}