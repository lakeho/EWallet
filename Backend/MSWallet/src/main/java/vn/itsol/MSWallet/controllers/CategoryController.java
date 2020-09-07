package vn.itsol.MSWallet.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.itsol.MSWallet.dto.CategoryDto;
import vn.itsol.MSWallet.service.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController
{
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "findall")
    public List<CategoryDto> getCategories()
    {
        List<CategoryDto> categoryList = categoryService.getCategories();
        return categoryList;
    }

    @GetMapping(path = "findbyid/{category_id}")
    public CategoryDto getCategory(@PathVariable("category_id") int category_id)
    {
        CategoryDto category = categoryService.getCategory(category_id);
        return category;
    }

    @PostMapping(path = "save")
    public void saveCategory(@RequestBody CategoryDto category)
    {
        categoryService.save(category);
    }

    @PutMapping(path = "update")
    public void updateCategory(@RequestBody CategoryDto category)
    {
        categoryService.update(category);
    }

    @DeleteMapping(path = "delete/{category_id}")
    public void deleteCategory(@PathVariable("category_id") int category_id)
    {
        categoryService.delete(category_id);
    }
}
