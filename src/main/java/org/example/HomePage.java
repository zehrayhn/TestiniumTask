package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class HomePage extends BasePage {

    By loginCookieRejectLocator = By.xpath("//*[@id=\'close-button-1454703513202\']");
    By cookieRejectLocator = new By.ByCssSelector("[id=\'CybotCookiebotDialogBodyLevelButtonLevelOptinDeclineAll\']");
    By popUpFirst = new By.ByCssSelector("[id=\'responsive-image-1689835577900100\']");
    By popUpSecond = new By.ByCssSelector("[id=\'CybotCookiebotDialog\']");
    By destinationLocator = new By.ByCssSelector("[placeholder=\'Otel Adı Veya Konum\']");
    By otelTabLocator = new By.ByCssSelector("[class=\'Tab__StyledTab-sc-1gyyeis-1 eDgwud\']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void fillOut(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement otel = wait.until(ExpectedConditions.elementToBeClickable(otelTabLocator));
        otel.click();
        String currentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
            }
        }

        WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement destination = wait.until(ExpectedConditions.elementToBeClickable(destinationLocator));
        destination.sendKeys(text);

    }

    public void acceptCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(popUpFirst));
            WebElement popupFirst = driver.findElement(popUpFirst);
            if (popupFirst.isDisplayed()) {
                popupFirst.findElement(loginCookieRejectLocator).click();
            }
        } catch (TimeoutException e) {

            System.err.println("İlk cookie gelmedi");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

        wait.until(ExpectedConditions.visibilityOfElementLocated(popUpSecond));

        WebElement popupSecond = driver.findElement(popUpSecond);
        if (popupSecond.isDisplayed()) {
            popupSecond.findElement(cookieRejectLocator).click();
        }
    }


    public void allowCookie() {

        Set<Cookie> cookies = driver.manage().getCookies();


        for (Cookie cookie : cookies) {
            System.out.println("Çerez Adı: " + cookie.getName());
            System.out.println("Çerez Değeri: " + cookie.getValue());

            // Çerezi kabul etmek için:
            driver.manage().addCookie(cookie);
        }
    }
}
