package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 标签控制层
 * Created by zhonglq on 2019/6/25.
 */
@CrossOrigin
@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping("list")
    public Result<List> findAll() {
        return new Result<>(true, StatusCode.OK, "success", labelService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Label> findById(@PathVariable String id) {
        return new Result<>(true, StatusCode.OK, "success", labelService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result searchLabel(@RequestBody Map searchMap) {
        List<Label> labelList = labelService.searchLabel(searchMap);
        return Result.ok(labelList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Label> labelPage = labelService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(labelPage.getTotalElements(), labelPage.getContent()));
    }

}
