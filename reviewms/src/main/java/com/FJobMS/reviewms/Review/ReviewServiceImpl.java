package com.FJobMS.reviewms.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewServiceImpl implements IReviewService {
    @Autowired
    private ReviewRepository repo;


    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = repo.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if (companyId != null) {
            review.setCompanyId(companyId);
            repo.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReview(Long reviewId) {
        return repo.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Optional<Review> reviewOpt = repo.findById(reviewId);
        if (reviewOpt.isPresent()){
            Review review = reviewOpt.get();
            review.setDescription(updatedReview.getDescription());
            review.setTitle(updatedReview.getTitle());
            review.setRating(updatedReview.getRating());
            repo.save(updatedReview);
            return true;
        } else {
            return false;
        }
    }


}