package com.FJobMS.reviewms.Review;

import com.FJobMS.reviewms.messaging.ReviewMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private IReviewService service;
    private ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewMessageProducer reviewMessageProducer, IReviewService service) {
        this.reviewMessageProducer = reviewMessageProducer;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(service.getAllReviews(companyId),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                            @RequestBody Review review) {
        boolean isReviewSaved = service.addReview(companyId, review);
        if (isReviewSaved){
            reviewMessageProducer.sendMessage(review);
        return new ResponseEntity<>("Review added Successfully",
                HttpStatus.OK);
    }
        else
            return new ResponseEntity<>("Review Not Saved",
                    HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        return new ResponseEntity<>(service.getReview(reviewId),
                HttpStatus.OK);
    }

    @PutMapping("/{reviwId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isReviewUpdated = service.updateReview(reviewId, review);
        if (isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully",
                    HttpStatus.OK);
        else
            return new ResponseEntity<>("Review updated successfully",
                    HttpStatus.NOT_FOUND);

    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId){
        List<Review> reviewList = service.getAllReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average()
                .orElse(0.0);
    }
}

