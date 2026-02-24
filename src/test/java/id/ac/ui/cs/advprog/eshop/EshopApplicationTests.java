package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void testMain() {
        // Panggil fungsi main buat dapet coverage 100% di EshopApplication
        EshopApplication.main(new String[] {});
    }
}
