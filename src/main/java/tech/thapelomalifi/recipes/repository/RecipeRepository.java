package tech.thapelomalifi.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.thapelomalifi.recipes.model.Category;
import tech.thapelomalifi.recipes.model.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findAllByNameContainingOrCategoryNameContainingOrDescriptionContaining(
            String name,
            String categoryName,
            String description
    );
}
