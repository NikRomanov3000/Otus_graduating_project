package ru.romanov.graduation.project.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.romanov.graduation.project.service.ReceiptService;
import ru.romanov.otus.model.PaymentInfo;

@EnableRabbit
@Component
public class RabbitMqListener {
    private final ReceiptService receiptService;

    public RabbitMqListener(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @RabbitListener(queues = "paymentQueue")
    public void processPaymentQueue(String message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentInfo paymentInfoFromRabbit;

        try {
            paymentInfoFromRabbit = objectMapper.readValue(message, PaymentInfo.class );
        } catch (JsonProcessingException ex){
            throw new RuntimeException("Message from RabbitMQ does not match PaymentInfo format");
        }

        receiptService.updateReceipt(paymentInfoFromRabbit);
    }
}
