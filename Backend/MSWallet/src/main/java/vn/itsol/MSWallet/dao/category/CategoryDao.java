package vn.itsol.MSWallet.dao.category;

import vn.itsol.MSWallet.entities.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getCategories();
    Category getCatetgory(int category_id);
    void save(Category category);
    void update(Category category);
    void delete(int category_id);
}
