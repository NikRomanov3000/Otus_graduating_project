package project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import project.service.PaymentService;
import ru.romanov.otus.model.Payment;
import ru.romanov.otus.model.PaymentInfo;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@ComponentScan("ru.romanov")
public class PaymentController {

    private PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping({"/payment"})
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayment();
    }

    @GetMapping({"/payment/{paymentId}"})
    public Optional<Payment> getPaymentById(@PathVariable(value = "paymentId") Long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping({"/payment"})
    public Long savePayment(@RequestBody Payment payment) throws Exception {
        Long retId = null;
        try {
            retId = paymentService.addPayment(payment).getId();

            PaymentInfo paymentInfo = new PaymentInfo(payment.getRefReceiptId(), payment.getAmount(), false);
            sendPaymentInformation(paymentInfo);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return retId;
    }

    @DeleteMapping({"/payment/{paymentId}"})
    public boolean deletePaymentById(@PathVariable(value = "paymentId") Long id) throws Exception {
        try {
            Payment payment = paymentService.getPaymentById(id).get();
            PaymentInfo paymentInfo = new PaymentInfo(payment.getRefReceiptId(), payment.getAmount(), true);
            sendPaymentInformation(paymentInfo);

            paymentService.removePaymentById(id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }

    private void sendPaymentInformation(PaymentInfo paymentInfo) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String serviceUri = "http://localhost:8080";
        HttpPut putRequestForUpdate = new HttpPut(serviceUri + "/api/receipt/" + paymentInfo.getReceiptId());
        ObjectMapper objectMapper = new ObjectMapper();
        putRequestForUpdate.setEntity(new StringEntity(objectMapper.writeValueAsString(paymentInfo)));
        putRequestForUpdate.setHeader("Accept", "application/json");
        putRequestForUpdate.setHeader("Content-type", "application/json");


        CloseableHttpResponse response = httpclient.execute(putRequestForUpdate);
    }
}
