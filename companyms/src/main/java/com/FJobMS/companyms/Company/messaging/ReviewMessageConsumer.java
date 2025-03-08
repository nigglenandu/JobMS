package com.FJobMS.companyms.Company.messaging;

import com.FJobMS.companyms.Company.ICompanyService;
import com.FJobMS.companyms.Company.dto.ReviewMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {
    private final ICompanyService companyService;

    public ReviewMessageConsumer(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {
        companyService.updateCompanyRating(reviewMessage);
    }
}
