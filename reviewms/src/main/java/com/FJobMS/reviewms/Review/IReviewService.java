package com.FJobMS.reviewms.Review;

import java.util.List;

public interface IReviewService {
    List<Review> getAllReviews(Long CompanyId);
    boolean addReview(Long companyId, Review review);
    Review getReview(Long reviewId);
    boolean updateReview(Long reviewId, Review review);
}
