package id.ac.ui.cs.advprog.eshop.functional;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomePageFunctionalTest extends BaseFunctionalTest {

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise: Buka halaman utama
        driver.get(baseUrl);
        String pageTitle = driver.getTitle();

        // Verify: Cek apakah title browser adalah "ADV Shop"
        assertEquals("ADV Shop", pageTitle);
    }

    @Test
    void welcomeMessage_homePage_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise: Buka halaman utama
        driver.get(baseUrl);

        // Mencari elemen <h3> di halaman web
        String welcomeMessage = driver.findElement(By.tagName("h3")).getText();

        // Verify: Cek apakah teksnya adalah "Welcome"
        assertEquals("Welcome", welcomeMessage);
    }
}