package tech.thapelomalifi.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.thapelomalifi.recipes.model.Rating;
import tech.thapelomalifi.recipes.model.Recipe;
import tech.thapelomalifi.recipes.model.User;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Optional<Rating> findByRaterAndRecipe(User authenticatedUser, Recipe recipe);
    List<Rating> findAllByRecipe(Recipe recipe);
    @Query("select avg(r.rating) from Rating r where r.recipe =:recipe")
    double findRatingAverageByRecipe(@Param("recipe") Recipe recipe);
}
