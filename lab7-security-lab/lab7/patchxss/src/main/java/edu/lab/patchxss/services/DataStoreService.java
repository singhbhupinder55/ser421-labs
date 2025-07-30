package edu.lab.patchxss.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import edu.lab.patchxss.models.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

@Service
public class DataStoreService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addReview(Review review) {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS reviews(username VARCHAR(100), productname VARCHAR(100), review VARCHAR(100))");

        String query = "INSERT INTO reviews values(?,?,?)";
        jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {  
                ps.setString(1, review.getUserName());  
                ps.setString(2, review.getProductName());  
                ps.setString(3, review.getReview());  
                return ps.execute();
            }  
        });
    }

    public List<Review> getReviews() {
        String t = jdbcTemplate.queryForObject("SELECT date('now')", String.class);
        System.out.println("CurrentDate:  " + t);

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS reviews(username VARCHAR(100), productname VARCHAR(100), review VARCHAR(100))");

        String sql = "SELECT * FROM reviews";
        List<Review> reviews = jdbcTemplate.query(sql, new RowMapper<Review>() {
            @Override
            public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
                Review review = new Review();
                review.setUserName(rs.getString(1));
                review.setProductName(rs.getString(2));
                review.setReview(rs.getString(3));
                return review;
            }
        });
        return reviews;
    }
}
