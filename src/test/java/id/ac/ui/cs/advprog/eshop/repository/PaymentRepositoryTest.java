package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSaveAndFind() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("1", "Voucher", "SUCCESS", data);
        paymentRepository.save(payment);

        Payment savedPayment = paymentRepository.findById("1");
        assertEquals(payment.getId(), savedPayment.getId());
    }
}