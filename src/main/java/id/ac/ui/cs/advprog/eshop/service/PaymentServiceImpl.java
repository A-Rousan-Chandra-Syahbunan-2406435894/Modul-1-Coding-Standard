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
        String status = "REJECTED";
        if (method.equals("VOUCHER")) {
            String code = paymentData.get("voucherCode");
            if (code != null && code.startsWith("ESHOP") && code.length() == 16 && code.replaceAll("[^0-9]", "").length() == 8) {
                status = "SUCCESS";
            }
        } else if (method.equals("BANK_TRANSFER")) {
            if (paymentData.get("bankName") != null && paymentData.get("referenceCode") != null) {
                status = "SUCCESS";
            }
        }
        Payment payment = new Payment(UUID.randomUUID().toString(), method, status, paymentData);
        return paymentRepository.save(payment);
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