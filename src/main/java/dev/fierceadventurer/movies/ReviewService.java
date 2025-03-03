package dev.fierceadventurer.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String ImdbId){
        if(reviewBody == null || reviewBody.trim().isEmpty()){
            throw new IllegalArgumentException("Review body cannot be empty");
        }
        if(ImdbId == null || ImdbId.trim().isEmpty()){
            throw new IllegalArgumentException("ImdbId cannot be empty");
        }
        Review review = reviewRepository.insert(new Review(reviewBody));



        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(ImdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        return review;
    }
}
