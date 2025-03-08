package com.FJobMS.reviewms.messaging;

import com.FJobMS.reviewms.Review.Review;
import com.FJobMS.reviewms.dto.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Review review){
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(review.getId());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setCompanyId(review.getCompanyId());
        reviewMessage.setRating(review.getRating());
        reviewMessage.setTitle(review.getTitle());
        reviewMessage.setCompanyId(review.getCompanyId());
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
    }
}
