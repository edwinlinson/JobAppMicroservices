package com.example.reviewms.Review.Messaging;

import com.example.reviewms.Review.Review;
import com.example.reviewms.Review.dto.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;
    public ReviewMessageProducer(RabbitTemplate  rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendMessage(Review review){
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setTitle(reviewMessage.getTitle());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setRating(review.getRating());
        reviewMessage.setId(review.getId());
        System.out.println(" reviewMessage is :"+reviewMessage);

        rabbitTemplate.convertAndSend("companyRatingQueue",reviewMessage);

    }
}
