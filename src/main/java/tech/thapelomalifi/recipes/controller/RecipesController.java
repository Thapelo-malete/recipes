package tech.thapelomalifi.recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.thapelomalifi.recipes.model.Rating;
import tech.thapelomalifi.recipes.model.Recipe;
import tech.thapelomalifi.recipes.service.RecipeService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/recipe")
public class RecipesController {
    private final RecipeService recipeService;

    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(recipeService.addRecipe(recipe), HttpStatus.OK);
    }

    @GetMapping(path = "/{recipeId}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable("recipeId") Integer id) {
        return new ResponseEntity<>(recipeService.getRecipe(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{recipeId}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("recipeId") Integer id, @RequestBody Recipe recipe) {
        return new ResponseEntity<>(recipeService.updateRecipe(recipe, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{recipeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable("recipeId") Integer id) {
        recipeService.deleteRecipe(id);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<Recipe>> searchRecipe(@RequestParam("q") String searchQuery) {
        return new ResponseEntity<>(recipeService.searchRecipe(searchQuery), HttpStatus.OK);
    }

    @PostMapping(path = "/{recipeId}/rate")
    public ResponseEntity<List<Rating>> rateRecipe(@PathVariable("recipeId") Integer recipeId, @RequestBody Rating rating) {
        return new ResponseEntity<>(recipeService.rateRecipe(recipeId, rating), HttpStatus.OK);
    }

    @GetMapping(path = "/{recipeId}/ratings")
    public ResponseEntity<List<Rating>> recipeRatings(@PathVariable("recipeId") Integer recipeId, @RequestBody Rating rating) {
        return new ResponseEntity<>(recipeService.rateRecipe(recipeId, rating), HttpStatus.OK);
    }

    @GetMapping(path = "/{recipeId}/rating")
    public ResponseEntity<Double> recipeRating(@PathVariable("recipeId") Integer recipeId) {
        return new ResponseEntity<>(recipeService.getRating(recipeId), HttpStatus.OK);
    }
}
