package com.lou.springboot.controller;

import com.lou.springboot.common.Constants;
import com.lou.springboot.common.Result;
import com.lou.springboot.common.ResultGenerator;
import com.lou.springboot.config.annotation.TokenToUser;
import com.lou.springboot.entity.AdminUser;
import com.lou.springboot.entity.Picture;
import com.lou.springboot.service.PictureService;
import com.lou.springboot.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 13
 * @qqäº¤æµ?ç¾¤ 796794009
 * @email 2449207463@qq.com
 * @link http://13blog.site
 */
@RestController
@RequestMapping("/pictures")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    /**
     * åˆ—è¡¨
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "å?‚æ•°å¼‚å¸¸ï¼?");
        }
        //æŸ¥è¯¢åˆ—è¡¨æ•°æ?®
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.genSuccessResult(pictureService.getPicturePage(pageUtil));
    }

    /**
     * ä¿¡æ?¯
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public Result info(@PathVariable("id") Integer id, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "æœªç™»å½•ï¼?");
        }
        if (id < 1) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "å?‚æ•°å¼‚å¸¸ï¼?");
        }
        Picture picture = pictureService.queryObject(id);
        if (picture == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "å?‚æ•°å¼‚å¸¸ï¼?");
        }
        return ResultGenerator.genSuccessResult(picture);
    }

    /**
     * ä¿?å­˜
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Picture picture, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "æœªç™»å½•ï¼?");
        }
        if (StringUtils.isEmpty(picture.getPath()) || StringUtils.isEmpty(picture.getRemark())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "å?‚æ•°å¼‚å¸¸ï¼?");
        }
        if (pictureService.save(picture) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("æ·»åŠ å¤±è´¥");
        }
    }

    /**
     * ä¿®æ”¹
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody Picture picture, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "æœªç™»å½•ï¼?");
        }
        if (null == picture.getId() || StringUtils.isEmpty(picture.getPath()) || StringUtils.isEmpty(picture.getRemark())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "å?‚æ•°å¼‚å¸¸ï¼?");
        }
        Picture tempPicture = pictureService.queryObject(picture.getId());
        if (tempPicture == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "å?‚æ•°å¼‚å¸¸ï¼?");
        }
        if (pictureService.update(picture) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("ä¿®æ”¹å¤±è´¥");
        }
    }

    /**
     * åˆ é™¤
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result delete(@RequestBody Integer[] ids, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "æœªç™»å½•ï¼?");
        }
        if (ids.length < 1) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "å?‚æ•°å¼‚å¸¸ï¼?");
        }
        if (pictureService.deleteBatch(ids) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("åˆ é™¤å¤±è´¥");
        }
    }

}
