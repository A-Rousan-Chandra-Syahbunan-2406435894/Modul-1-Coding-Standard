package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

// Anotasi ini dipindah ke Base Class biar anaknya gak usah nulis lagi
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public abstract class BaseFunctionalTest {

    @LocalServerPort
    protected int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    protected String testBaseUrl;

    protected String baseUrl;

    @BeforeEach
    void setupTest() {
        // Otomatis bikin baseUrl (contoh: http://localhost:8080) buat dipake anak-anaknya
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }
}