package vn.itsol.MSWallet.service.category;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.itsol.MSWallet.dao.category.CategoryDao;
import vn.itsol.MSWallet.dto.CategoryDto;
import vn.itsol.MSWallet.entities.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categoryList = categoryDao.getCategories();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category c : categoryList){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryid(c.getCategoryid());
            categoryDto.setCategoryName(c.getCategoryname());
            categoryDtos.add(categoryDto);
        }
        log.info("getCategories.categoryDtos: " + categoryDtos.toString());
        return categoryDtos;
    }

    @Transactional
    @Override
    public CategoryDto getCategory(int category_id) {
        Category category = categoryDao.getCatetgory(category_id);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryid(category.getCategoryid());
        categoryDto.setCategoryName(category.getCategoryname());
        log.info("getCategories.getCategory: " + categoryDto.toString());
        return categoryDto;
    }

    @Transactional
    @Override
    public void save(CategoryDto category) {
        Category category1 = new Category();
//        category1.setCategoryid(category.getCategoryid());
        category1.setCategoryname(category.getCategoryName());
        categoryDao.save(category1);
    }

    @Transactional
    @Override
    public void update(CategoryDto category) {
        Category category1 = new Category();
        category1.setCategoryid(category.getCategoryid());
        category1.setCategoryname(category.getCategoryName());
        categoryDao.update(category1);
    }

    @Transactional
    @Override
    public void delete(int category_id) {
        categoryDao.delete(category_id);
    }
}
