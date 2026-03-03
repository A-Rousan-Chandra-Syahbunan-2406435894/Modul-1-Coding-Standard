package id.ac.ui.cs.advprog.eshop.functional;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Cukup extends, sisanya udah diurus bapaknya
class CreateProductFunctionalTest extends BaseFunctionalTest {

    @Test
    void testCreateProductIsSuccessful(ChromeDriver driver) throws Exception {
        // Pake variabel baseUrl dari bapaknya
        String listUrl = baseUrl + "/product/list";

        // 1. Pergi ke halaman list, lalu klik tombol Create
        driver.get(listUrl);
        driver.findElement(By.linkText("Create Product")).click();

        // 2. Isi Form
        driver.findElement(By.id("nameInput")).sendKeys("Sampo Cap Bambang");
        driver.findElement(By.id("quantityInput")).sendKeys("100");

        // 3. Submit
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 4. Verifikasi apakah balik ke halaman list dan barang muncul
        String currentUrl = driver.getCurrentUrl();
        assertEquals(listUrl, currentUrl);

        WebElement productName = driver.findElement(By.xpath("//td[contains(text(), 'Sampo Cap Bambang')]"));
        assertEquals("Sampo Cap Bambang", productName.getText());
    }
}