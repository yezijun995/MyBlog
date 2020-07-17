package edu.fdzc.service.impl;

import edu.fdzc.entity.Type;
import edu.fdzc.mapper.ITypeMapper;
import edu.fdzc.queryvo.TypeBar;
import edu.fdzc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private ITypeMapper typeMapper;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);
    }

    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Transactional
    @Override
    public List<TypeBar> getTypeSortById(Long size) {
        return typeMapper.getTypeSortById(size);
    }

    @Transactional
    @Override
    public List<TypeBar> getAllTypeAndBlog() {
        return typeMapper.getAllTypeAndBlog();
    }
}
