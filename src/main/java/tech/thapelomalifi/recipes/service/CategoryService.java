package tech.thapelomalifi.recipes.service;

import org.springframework.stereotype.Service;
import tech.thapelomalifi.recipes.exceptions.CategoryAlreadyAddedException;
import tech.thapelomalifi.recipes.exceptions.CategoryNotFoundException;
import tech.thapelomalifi.recipes.model.Category;
import tech.thapelomalifi.recipes.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category requestCategory) {
        Optional<Category> category = categoryRepository.findByNameIgnoreCase(requestCategory.getName());

        if (category.isPresent()) {
            throw new CategoryAlreadyAddedException("Category with the name, " +requestCategory.getName()+ " already exists");
        }

        return categoryRepository.save(requestCategory);
    }

    public void deleteCategory(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category " +id+ " does not exist");
        }

        categoryRepository.deleteById(id);
    }

    public List<Category> allCategories(){
        return  categoryRepository.findAll();
    }
}
