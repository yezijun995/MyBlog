package edu.fdzc.queryvo;

/**
 * 搜索博客类
 */
public class SearchBlog {

    private Long typeId;

    private String title;

    public SearchBlog() {
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SearchBlog{" +
                "typeId=" + typeId +
                ", title='" + title + '\'' +
                '}';
    }
}
