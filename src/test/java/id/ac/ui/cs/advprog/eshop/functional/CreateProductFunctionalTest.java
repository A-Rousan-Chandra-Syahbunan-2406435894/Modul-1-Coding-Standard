package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String baseUrl;

    private String url;

    @BeforeEach
    void setupTest() {
        url = String.format("%s:%d/product/list", baseUrl, serverPort);
    }

    @Test
    void testCreateProductIsSuccessful(ChromeDriver driver) throws Exception {
        // 1. Pergi ke halaman list, lalu klik tombol Create
        driver.get(url);
        driver.findElement(By.linkText("Create Product")).click();

        // 2. Isi Form
        driver.findElement(By.id("nameInput")).sendKeys("Sampo Cap Bambang");
        driver.findElement(By.id("quantityInput")).sendKeys("100");

        // 3. Submit
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 4. Verifikasi apakah balik ke halaman list dan barang muncul
        String currentUrl = driver.getCurrentUrl();
        assertEquals(url, currentUrl);

        WebElement productName = driver.findElement(By.xpath("//td[contains(text(), 'Sampo Cap Bambang')]"));
        assertEquals("Sampo Cap Bambang", productName.getText());
    }
}