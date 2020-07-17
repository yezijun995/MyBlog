package edu.fdzc.queryvo;

import edu.fdzc.entity.Blog;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类实体类
 */
public class TypeBar {

    private Long typeId;
    /*分类名*/
    private String name;

    private Long typeCount;


    public TypeBar() {
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(Long typeCount) {
        this.typeCount = typeCount;
    }

    @Override
    public String toString() {
        return "TypeBar{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                ", typeCount=" + typeCount +
                '}';
    }
}
