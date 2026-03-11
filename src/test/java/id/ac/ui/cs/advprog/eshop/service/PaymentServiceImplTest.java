package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        List<Product> products = new ArrayList<>();
        products.add(new Product());
        Order order = new Order("1", products, 1708560000L, "Bambang");


        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArguments()[0]);


        Payment payment = paymentService.addPayment(order, "VOUCHER", data);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testAddPaymentBankTransferSuccess() {
        Map<String, String> data = new HashMap<>();
        data.put("bankName", "BCA");
        data.put("referenceCode", "12345");

        List<Product> products = new ArrayList<>();
        products.add(new Product());
        Order order = new Order("1", products, 1708560000L, "Bambang");


        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArguments()[0]);


        Payment payment = paymentService.addPayment(order, "BANK_TRANSFER", data);
        assertEquals("SUCCESS", payment.getStatus());
    }
    @Test
    void testAddPaymentBankTransferRejectedIfEmpty() {
        Map<String, String> data = new HashMap<>();
        // Sengaja data kosong/null biar dapet status REJECTED
        Payment payment = paymentService.addPayment(new Order(), "BANK_TRANSFER", data);
        assertEquals("REJECTED", payment.getStatus());
    }
}