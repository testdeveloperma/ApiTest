package com.woreye.controller;

import com.woreye.enumeration.Status;
import com.woreye.framework.JsonResult;
import com.woreye.framework.base.BaseController;
import com.woreye.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistic")
public class StatisticsController extends BaseController{

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/testcase_statistic")
    @ResponseBody
    JsonResult getTestCaseStatistic(@RequestParam("testCaseId") String testCaseId){
        return new JsonResult(Status.SUCCESS, statisticsService.getTestCaseResultStatistic(testCaseId));
    }

    @GetMapping("/collection_statistic")
    @ResponseBody
    JsonResult getCollectionStatistic(@RequestParam("collectionId") String collectionId){
        return new JsonResult(Status.SUCCESS, statisticsService.getCollectionResultStatistic(collectionId));
    }

}
