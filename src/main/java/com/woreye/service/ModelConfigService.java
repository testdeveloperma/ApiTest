package com.woreye.service;

import com.github.pagehelper.PageHelper;
import com.woreye.mapper.mapperOffLine.ModelConfigMapper;
import com.woreye.pojo.ModelConfig;
import com.woreye.utils.BeanUtil;
import com.woreye.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 17-4-28.
 */
@Service
public class ModelConfigService {
    @Autowired
    ModelConfigMapper modelConfigMapper;


    public void update( ModelConfig m){
        modelConfigMapper.update(m);
    }

    public void save(ModelConfig m){
        modelConfigMapper.save(m);
    }
    public int del(String id) {return modelConfigMapper.del(id);}
    public ModelConfig getById(String id) {return modelConfigMapper.getById(id);}

    public PageResult<ModelConfig> queryByPage(Integer pageNo, Integer pageSize, String modelName, String uid){
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;

        PageHelper.startPage(pageNo, pageSize);

        return BeanUtil.toPagedResult(modelConfigMapper.queryByPage(modelName,uid));
    }
}
