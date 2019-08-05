package com.woreye.controller;

import com.woreye.enumeration.Status;
import com.woreye.framework.JsonResult;
import com.woreye.framework.base.BaseController;
import com.woreye.pojo.CollectionResult;
import com.woreye.pojo.po.TestCaseResultPoForTable;
import com.woreye.pojo.po.statistic.CollectionResultStatisticInfo;
import com.woreye.pojo.vo.CollectionVo;
import com.woreye.service.CollectionService;
import com.woreye.service.RunTestCaseService;
import com.woreye.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/collection")
public class CollectionController extends BaseController {
    @Autowired
    CollectionService collectionService;

    @Autowired
    RunTestCaseService runTestCaseService;

    @PostMapping("/add")
    @ResponseBody
    JsonResult addCollection(@RequestBody CollectionVo collectionVo){
        collectionService.addCollection(collectionVo);
        return new JsonResult(Status.SUCCESS);
    }

    @PostMapping("/update")
    @ResponseBody
    JsonResult updateCollection(@RequestBody CollectionVo collectionVo){
        collectionService.updateCollection(collectionVo);
        return new JsonResult(Status.SUCCESS);
    }

    @GetMapping("/get_all_collections")
    @ResponseBody
    JsonResult getAllCollections(@RequestParam("projectId") String projectId){
        return new JsonResult(Status.SUCCESS, collectionService.getAllCollections(projectId));
    }

    @GetMapping(value = "/run_collection")
    @ResponseBody
    public JsonResult runCollection(@RequestParam("collectionId") String collectionId) {
        String uid = Tools.getUser().getId();
        runTestCaseService.runCollectionId(collectionId, uid);
        return new JsonResult(Status.SUCCESS);
    }

    @PostMapping("/get_collection_test_case")
    @ResponseBody
    JsonResult getCollectionTestCase(@RequestBody CollectionVo collectionVo) {
        return new JsonResult(Status.SUCCESS, collectionService.getCollectionTestCase(collectionVo));
    }

    @GetMapping("/get_collection_result")
    @ResponseBody
    JsonResult getCollectionResult(@RequestParam("collectionId") String collectionId){
        List<CollectionResult> listCollectionResult = collectionService.getCollectionResult(collectionId);
        return new JsonResult(Status.SUCCESS, listCollectionResult);
    }

    @GetMapping("/collection_result_detail")
    @ResponseBody
    JsonResult getCollectionTestCaseResult(@RequestParam("collectionResultId") String collectionResultId){

        List<TestCaseResultPoForTable> listCollectionResult = collectionService.getCollectionTestCaseResult(collectionResultId);
        return new JsonResult(Status.SUCCESS, listCollectionResult);
    }

    @GetMapping("/collection_result_statistic")
    @ResponseBody
    JsonResult getCollectionStatistic(@RequestParam("collectionResultId") String collectionResultId){
        CollectionResultStatisticInfo collectionResultStatisticInfo = collectionService.queryCollectionResultInfo(collectionResultId);
        return new JsonResult(Status.SUCCESS, collectionResultStatisticInfo);
    }
    @GetMapping("/remove_test_case")
    @ResponseBody
    JsonResult removeTestCase(@RequestParam("collectionId") String collectionId, @RequestParam("testCaseId") String testCaseId){
        collectionService.removeTestCaseId(collectionId, testCaseId);
        return new JsonResult(Status.SUCCESS);
    }
}
