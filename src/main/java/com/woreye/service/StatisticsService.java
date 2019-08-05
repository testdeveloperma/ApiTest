package com.woreye.service;


import com.woreye.pojo.po.statistic.ResponseTimeInfoPo;
import com.woreye.pojo.po.statistic.StatisticResultOverViewInfoPo;
import com.woreye.pojo.po.statistic.TimeSuccessFailedCountPo;
import com.woreye.pojo.vo.statistic.CollectionResultStatistic;
import com.woreye.pojo.vo.statistic.TestCaseResultStatistic;

import java.util.List;

public interface StatisticsService {

    List<ResponseTimeInfoPo> queryResponseTimeIn30Days(String testCaseId);


    StatisticResultOverViewInfoPo queryTestCaseResultOverviewInfo(String testCaseId);

    StatisticResultOverViewInfoPo queryCollectionResultOverviewInfo(String collectionId);

    List<TimeSuccessFailedCountPo> queryTestCaseSuccessFailedCount(String testCaseId);

    List<TimeSuccessFailedCountPo> queryCollectionSuccessFailedCount(String collectionId);

    TestCaseResultStatistic getTestCaseResultStatistic(String testCaseId);

    CollectionResultStatistic getCollectionResultStatistic(String collectionId);

}
