package edu.fdzc.mapper;

import edu.fdzc.entity.Type;
import edu.fdzc.queryvo.TypeBar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ITypeMapper {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    int updateType(Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);

    List<TypeBar> getTypeSortById(Long size);

    List<TypeBar> getAllTypeAndBlog();
}
