package com.woreye.service;


import com.woreye.pojo.CollectionResult;
import com.woreye.pojo.CollectionTestCase;
import com.woreye.pojo.po.TestCaseResultPoForTable;
import com.woreye.pojo.po.statistic.CollectionResultStatisticInfo;
import com.woreye.pojo.vo.CollectionVo;

import java.util.List;

public interface CollectionService {

    void addCollection(CollectionVo collectionVo);

    List<CollectionVo> getAllCollections(String projectId);

    List<CollectionTestCase> getCollectionTestCase(CollectionVo collectionVo);

    void runCollection(String collectionId);

    void updateCollection(CollectionVo collectionVo);

    CollectionResultStatisticInfo queryCollectionResultInfo(String collectionResultId);

    List<CollectionResult> getCollectionResult(String collectionId);

    List<TestCaseResultPoForTable> getCollectionTestCaseResult(String collectionResultId);

    void removeTestCaseId(String collectionId, String testCaseId);
}
