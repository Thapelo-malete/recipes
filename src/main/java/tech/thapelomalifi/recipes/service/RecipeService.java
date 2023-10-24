package tech.thapelomalifi.recipes.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.thapelomalifi.recipes.exceptions.RatingOutOfBoundException;
import tech.thapelomalifi.recipes.exceptions.RecipeNotFoundException;
import tech.thapelomalifi.recipes.model.Rating;
import tech.thapelomalifi.recipes.model.Recipe;
import tech.thapelomalifi.recipes.model.User;
import tech.thapelomalifi.recipes.repository.RatingRepository;
import tech.thapelomalifi.recipes.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final UserService userService;
    private final RecipeRepository recipeRepository;
    private final RatingRepository ratingRepository;

    public RecipeService(UserService userService, RecipeRepository recipeRepository, RatingRepository ratingRepository) {
        this.userService = userService;
        this.recipeRepository = recipeRepository;
        this.ratingRepository = ratingRepository;
    }

    public Recipe addRecipe(Recipe recipe) {
        User user = userService.getAuthenticatedUser();
        recipe.setAuthor(user);
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Recipe requestRecipe, Integer id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(
                        ()-> new RecipeNotFoundException("Recipe "+id+" c it does not exist")
                );

        if (!userService.getAuthenticatedUser().equals(recipe.getAuthor())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        recipe.setCategory(requestRecipe.getCategory());
        recipe.setDescription(requestRecipe.getDescription());
        recipe.setDirections(requestRecipe.getDirections());
        recipe.setIngredients(requestRecipe.getIngredients());
        recipe.setName(recipe.getName());

        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Integer id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(
                        ()-> new RecipeNotFoundException("Recipe "+id+"  does not exist")
                );

        if (!userService.getAuthenticatedUser().equals(recipe.getAuthor())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        recipeRepository.deleteById(id);
    }


    public Recipe getRecipe(Integer id) {
        return recipeRepository.findById(id)
                .orElseThrow(
                        ()-> new RecipeNotFoundException("Recipe "+id+"  does not exist")

                );
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> searchRecipe(String searchQuery) {
        return recipeRepository.findAllByNameContainingOrCategoryNameContainingOrDescriptionContaining(
                searchQuery,
                searchQuery,
                searchQuery
        );
    }

    public List<Rating> rateRecipe(Integer recipeId, Rating requestRating) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(
                        ()-> new RecipeNotFoundException("Recipe "+recipeId+"  does not exist")
                );

        Optional<Rating> rating = ratingRepository.findByRaterAndRecipe(userService.getAuthenticatedUser(), recipe);
        if (rating.isPresent()) {
            Rating dbRating = rating.get();
            if (requestRating.getRating() < 1 || requestRating.getRating() > 5) {
                throw new RatingOutOfBoundException("Your rating has to be between 1 and 5.");
            }
            dbRating.setRating(requestRating.getRating());
            ratingRepository.save(dbRating);
        } else {
            requestRating.setRecipe(recipe);
            requestRating.setRater(userService.getAuthenticatedUser());
            ratingRepository.save(requestRating);
        }

        return ratingRepository.findAllByRecipe(recipe);
    }

    public Double getRating(Integer recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(
                        ()-> new RecipeNotFoundException("Recipe "+recipeId+"  does not exist")
                );
        return ratingRepository.findRatingAverageByRecipe(recipe);
    }
}