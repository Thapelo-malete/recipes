package tech.thapelomalifi.recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.thapelomalifi.recipes.model.Category;
import tech.thapelomalifi.recipes.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/admin")
public class AdminController {
    private final CategoryService categoryService;

    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category/new")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/category/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<Category>> allCategories(){
        return new ResponseEntity<>(categoryService.allCategories(), HttpStatus.OK);
    }
}
