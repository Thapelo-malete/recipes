package tech.thapelomalifi.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.thapelomalifi.recipes.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByNameIgnoreCase(String categoryName);
}
