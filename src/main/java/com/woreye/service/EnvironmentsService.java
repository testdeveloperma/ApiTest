package com.woreye.service;

import com.github.pagehelper.PageHelper;
import com.woreye.mapper.mapperOffLine.EnvironmentsMapper;
import com.woreye.pojo.EnvironmentCollection;
import com.woreye.pojo.po.EnvironmentPo;
import com.woreye.pojo.vo.EnvironmentVo;
import com.woreye.utils.BeanUtil;
import com.woreye.utils.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.woreye.utils.ConverterVoPo.environmentPoToVoList;
import static com.woreye.utils.ConverterVoPo.environmentVoToPo;

@Service("EnvironmentsService")
public class EnvironmentsService {
    @Autowired
    EnvironmentsMapper environmentsMapper;

    public List<EnvironmentVo> queryEnvironments(String uid, String projectId){
        List<EnvironmentPo> po = environmentsMapper.queryEnvironments(uid, projectId);
        return environmentPoToVoList(po);
    }

    public void save(EnvironmentVo environment){
        environmentsMapper.save(environmentVoToPo(environment));
    }

    public int deleteById(EnvironmentVo environments){
        return environmentsMapper.deleteById(environments);
    }

    public void update(EnvironmentVo environment){
        environmentsMapper.update(environmentVoToPo(environment));
    }

    public PageResult<EnvironmentCollection> queryEnvironmentCollectionByPage(Integer pageNo, Integer pageSize, String name, String uid){
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;

        PageHelper.startPage(pageNo, pageSize);

        return BeanUtil.toPagedResult(environmentsMapper.queryEnvironmentCollectionByPage(name, uid));
    }

    public int delEnvironmentCollectionById(EnvironmentCollection environmentCollection){
        return environmentsMapper.delEnvironmentCollection(environmentCollection);
    }
    public void saveEnvironmentCollection(EnvironmentCollection environmentCollection){
        environmentsMapper.saveEnvironmentCollection(environmentCollection);
    }

    public void updateEnvironmentCollection(EnvironmentCollection environmentCollection){
        environmentsMapper.updateEnvironmentCollection(environmentCollection);
    }



//    public String findByName(String name){
//        return  environmentsMapper.findByName(name);
//    }
}
