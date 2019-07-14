package com.springboot.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.mapper.SeqenceMapper;
import com.springboot.service.IService;

import tk.mybatis.mapper.common.Mapper;

public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected Mapper<T> mapper;
    @Autowired
    protected SeqenceMapper seqenceMapper;
    
    public Mapper<T> getMapper() {
        return mapper;
    }
    @Override
    public Long getSequence(@Param("seqName") String seqName){
    	return seqenceMapper.getSequence(seqName);
    }
    
    @Override
    public List<T> selectAll() {
        //说明：查询所有数�?�
        return mapper.selectAll();
    }
    
    @Override
    public T selectByKey(Object key) {
        //说明：根�?�主键字段进行查询，方法�?�数必须包�?�完整的主键属性，查询�?�件使用等�?�
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int save(T entity) {
        //说明：�?存一个实体，null的属性也会�?存，�?会使用数�?�库默认值
        return mapper.insert(entity);
    }

    @Override
    public int delete(Object key) {
        //说明：根�?�主键字段进行删除，方法�?�数必须包�?�完整的主键属性
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int updateAll(T entity) {
        //说明：根�?�主键更新实体全部字段，null值会被更新
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        //根�?�主键更新属性�?为null的值
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectByExample(Object example) {
        //说明：根�?�Example�?�件进行查询
        //�?点：这个查询支�?通过Example类指定查询列，通过selectProperties方法指定查询列
        return mapper.selectByExample(example);
    }
}
