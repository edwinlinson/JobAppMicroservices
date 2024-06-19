package com.example.companyms.Company.Messaging;

import com.example.companyms.Company.CompanyService;
import com.example.companyms.Company.dto.ReviewMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    private CompanyService companyService;
    public MessageConsumer (CompanyService companyService){
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        companyService.updateCompanyRating(reviewMessage);
    }
}
