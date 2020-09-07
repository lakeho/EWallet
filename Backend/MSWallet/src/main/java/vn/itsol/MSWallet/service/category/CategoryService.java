package vn.itsol.MSWallet.service.category;

import vn.itsol.MSWallet.dto.CategoryDto;
import vn.itsol.MSWallet.entities.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategories();
    CategoryDto getCategory(int category_id);
    void save(CategoryDto category);
    void update(CategoryDto category);
    void delete(int category_id);
}
