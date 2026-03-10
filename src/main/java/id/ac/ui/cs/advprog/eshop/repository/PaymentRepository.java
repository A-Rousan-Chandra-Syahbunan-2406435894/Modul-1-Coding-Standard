package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PaymentRepository {
    private final List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        int index = -1;
        for (int i = 0; i < paymentData.size(); i++) {
            if (paymentData.get(i).getId().equals(payment.getId())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            paymentData.set(index, payment);
        } else {
            paymentData.add(payment);
        }
        return payment;
    }

    public Payment findById(String id) {
        return paymentData.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Payment> findAll() {
        return Collections.unmodifiableList(paymentData);
    }
}