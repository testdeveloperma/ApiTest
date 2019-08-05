package com.woreye.controller;

import com.woreye.enumeration.Status;
import com.woreye.framework.JsonResult;
import com.woreye.framework.base.BaseController;
import com.woreye.pojo.User;
import com.woreye.service.RunTestCaseService;
import com.woreye.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController extends BaseController{
    @Autowired
    RunTestCaseService runTestCaseService;

    @GetMapping(value = "/run_collection")
    @ResponseBody
    public JsonResult runCollection(@RequestParam("collectionId") String collectionId) {
        User user = Tools.getUserByToken();
        if (null == user){
            return new JsonResult("000013");
        }
        String uid = user.getId();
        Boolean successFlag = runTestCaseService.runCollectionId(collectionId, uid);
        if (!successFlag){
            return new JsonResult("000014");
        }
        return new JsonResult(Status.SUCCESS);
    }

}
