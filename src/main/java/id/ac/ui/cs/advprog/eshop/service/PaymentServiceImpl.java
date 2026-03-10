package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String status = validatePayment(method, paymentData);
        Payment payment = createPaymentObject(method, status, paymentData);
        return paymentRepository.save(payment);
    }


    private String validatePayment(String method, Map<String, String> paymentData) {
        if ("VOUCHER".equals(method)) {
            String code = paymentData.get("voucherCode");
            if (code != null && code.startsWith("ESHOP") && code.length() == 16 && code.replaceAll("[^0-9]", "").length() == 8) {
                return "SUCCESS";
            }
        } else if ("BANK_TRANSFER".equals(method)) {
            if (paymentData.get("bankName") != null && paymentData.get("referenceCode") != null) {
                return "SUCCESS";
            }
        }
        return "REJECTED";
    }

    private Payment createPaymentObject(String method, String status, Map<String, String> paymentData) {
        return Payment.builder()
                .id(UUID.randomUUID().toString())
                .method(method)
                .status(status)
                .paymentData(paymentData)
                .build();
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(String paymentId) { return paymentRepository.findById(paymentId); }

    @Override
    public List<Payment> getAllPayments() { return paymentRepository.findAll(); }
}