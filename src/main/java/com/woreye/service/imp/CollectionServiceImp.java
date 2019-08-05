package com.woreye.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.woreye.mapper.mapperOffLine.CollectionMapper;
import com.woreye.mapper.mapperOffLine.TestCaseResultMapper;
import com.woreye.pojo.CollectionResult;
import com.woreye.pojo.CollectionTestCase;
import com.woreye.pojo.po.CollectionPo;
import com.woreye.pojo.po.TestCaseResultPoForTable;
import com.woreye.pojo.po.statistic.CollectionResultStatisticInfo;
import com.woreye.pojo.vo.CollectionVo;
import com.woreye.service.CollectionService;
import com.woreye.utils.IdGen;
import com.woreye.utils.MyString;
import com.woreye.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImp implements CollectionService {
    @Autowired
    CollectionMapper collectionMapper;

    @Autowired
    TestCaseResultMapper testCaseResultMapper;

    @Override
    public void addCollection(CollectionVo collectionVo) {
        collectionMapper.add(collectionVoToPo(collectionVo));
    }

    @Override
    public List<CollectionVo> getAllCollections(String projectId) {
        List<CollectionPo> listCollectionPo = collectionMapper.queryAllCollections(projectId, Tools.getUser().getId());
        List<CollectionVo> listCollectionVo = new ArrayList<>();
        listCollectionPo.stream().forEach(x->{
            listCollectionVo.add(collectionPoToVo(x));
        });
        return listCollectionVo;
    }

    @Override
    public List<CollectionTestCase> getCollectionTestCase(CollectionVo collectionVo) {
        CollectionPo collectionPo = collectionMapper.queryCollection(collectionVo.getId(), Tools.getUser().getId());
        List<String> arrayTestCasesId = JSONArray.parseArray(collectionPo.getTestCases(), String.class);
        if (arrayTestCasesId.isEmpty()){
            return new ArrayList<>();
        }
        collectionVo.setTestCases(arrayTestCasesId);
        return collectionMapper.queryCollectionTestCase(collectionVo);
    }

    @Override
    public void runCollection(String collectionId) {

    }

    @Override
    public void updateCollection(CollectionVo collectionVo) {
        collectionMapper.update(collectionVoToPo(collectionVo));
    }

    @Override
    public CollectionResultStatisticInfo queryCollectionResultInfo(String collectionResultId) {
        CollectionResultStatisticInfo collectionResultStatisticInfo = collectionMapper.queryCollectionResultInfo(collectionResultId);
        collectionResultStatisticInfo.setTotalCase(collectionResultStatisticInfo.getTotalSuccessCase() + collectionResultStatisticInfo.getTotalFailedCase());
        return collectionResultStatisticInfo;
    }

    @Override
    public List<CollectionResult> getCollectionResult(String collectionId) {
        return collectionMapper.queryCollectionResult(collectionId);
    }

    @Override
    public List<TestCaseResultPoForTable> getCollectionTestCaseResult(String collectionResultId) {
        return testCaseResultMapper.queryCollectionTestCaseResult(collectionResultId);
    }

    @Override
    public void removeTestCaseId(String collectionId, String testCaseId) {
        CollectionPo collectionPo = collectionMapper.queryCollection(collectionId, Tools.getUser().getId());
        JSONArray arrayTestCase = JSONArray.parseArray(collectionPo.getTestCases());
        arrayTestCase.remove(testCaseId);
        String testCases = JSON.toJSONString(arrayTestCase);
        collectionPo.setTestCases(testCases);
        collectionMapper.update(collectionPo);
    }

    private CollectionPo collectionVoToPo(CollectionVo collectionVo){
        CollectionPo collectionPo = new CollectionPo();
        if (MyString.isEmpty(collectionVo.getId())){
            collectionPo.setId(IdGen.uuid());
        }else {
            collectionPo.setId(collectionVo.getId());
        }
        if (MyString.isEmpty(collectionVo.getCreateBy())){
            collectionPo.setCreateBy(Tools.getUser().getId());
        }else {
            collectionPo.setCreateBy(collectionVo.getCreateBy());
        }
        collectionPo.setCreateDate(collectionVo.getCreateDate());
        collectionPo.setTestCaseNum(collectionVo.getTestCases().size());
        collectionPo.setRemarks(collectionVo.getRemarks());
        collectionPo.setCollectionName(collectionVo.getCollectionName());
        collectionPo.setProjectId(collectionVo.getProjectId());
        collectionPo.setEnvironmentId(collectionVo.getEnvironmentId());
        collectionPo.setTestCases(JSON.toJSONString(collectionVo.getTestCases()));
        return collectionPo;
    }

    private CollectionVo collectionPoToVo(CollectionPo collectionPo){
        CollectionVo collectionVo = new CollectionVo();
        collectionVo.setId(collectionPo.getId());
        collectionVo.setCreateBy(collectionPo.getCreateBy());
        collectionVo.setEnvironmentId(collectionPo.getEnvironmentId());
        collectionVo.setRemarks(collectionPo.getRemarks());
        collectionVo.setProjectId(collectionPo.getProjectId());
        collectionVo.setCollectionName(collectionPo.getCollectionName());
        collectionVo.setCreateDate(collectionPo.getCreateDate());
        collectionVo.setTestCaseNum(collectionPo.getTestCaseNum());
        collectionVo.setIsSuccess(collectionPo.getIsSuccess());
        collectionVo.setRunDate(collectionPo.getRunDate());
        collectionVo.setTestCases(JSONArray.parseArray(collectionPo.getTestCases(), String.class));
        return collectionVo;
    }

}
