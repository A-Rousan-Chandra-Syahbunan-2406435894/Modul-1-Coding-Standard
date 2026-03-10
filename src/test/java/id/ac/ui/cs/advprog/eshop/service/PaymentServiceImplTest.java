package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Test
    void testAddPaymentVoucherSuccess() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = paymentService.addPayment(new Order(), "VOUCHER", data);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testAddPaymentBankTransferSuccess() {
        Map<String, String> data = new HashMap<>();
        data.put("bankName", "BCA");
        data.put("referenceCode", "12345");
        Payment payment = paymentService.addPayment(new Order(), "BANK_TRANSFER", data);
        assertEquals("SUCCESS", payment.getStatus());
    }
}