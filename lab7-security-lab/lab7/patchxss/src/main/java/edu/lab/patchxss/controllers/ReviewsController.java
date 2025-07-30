package edu.lab.patchxss.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.lab.patchxss.models.Review;
import edu.lab.patchxss.services.DataStoreService;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    @Autowired
    private DataStoreService dataStoreService;

	@PostMapping
	public void addReview(@RequestBody Review review) {
		System.out.println("Review: " + review.getReview());
        dataStoreService.addReview(review);
	}

    @GetMapping
    public List<Review> getReviews() {
        return dataStoreService.getReviews();
    }
}