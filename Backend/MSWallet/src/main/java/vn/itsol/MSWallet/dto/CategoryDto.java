package vn.itsol.MSWallet.dto;

import javax.persistence.Column;

public class CategoryDto
{
    private long categoryid;
    private String categoryname;

    public long getCategoryid() {
        return categoryid;
    }

    public String getCategoryName() {
        return categoryname;
    }
    public void setCategoryid(long category) {
        this.categoryid = category;
    }

    public void setCategoryName(String categoryName) {
        this.categoryname = categoryName;
    }

    public CategoryDto(long category, String categoryName) {
        this.categoryid = category;
        this.categoryname = categoryName;
    }

    public CategoryDto() {
    }
}
