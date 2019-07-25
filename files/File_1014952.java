package com.kakarote.crm9.erp.bi.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.kakarote.crm9.erp.bi.service.BiFunnelService;

public class BiFunnelController extends Controller {
    @Inject
    private BiFunnelService service;
    /**
     * 销售�?斗
     * @author zxy
     */
    public void sellFunnel(@Para("deptId")Integer deptId, @Para("userId")Long userId, @Para("type")String type,
                           @Para("startTime") String startTime, @Para("endTime")String endTime,@Para("typeId")Integer typeId){
        renderJson(service.sellFunnel(deptId,userId,type,startTime,endTime,typeId));
    }
    /**
     * 新增商机分�?图
     * @author zxy
     */
    public void addBusinessAnalyze(@Para("deptId")Integer deptId, @Para("userId")Long userId, @Para("type")String type,
                           @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.addBusinessAnalyze(deptId,userId,type,startTime,endTime));
    }
    /**
     * 新增商机分�?表
     * @author zxy
     */
    public void sellFunnelList(@Para("deptId")Integer deptId, @Para("userId")Long userId, @Para("type")String type,
                                   @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.sellFunnelList(deptId,userId,type,startTime,endTime));
    }
    /**
     * 商机转化率分�?
     * @author zxy
     */
    public void win(@Para("deptId")Integer deptId, @Para("userId")Long userId, @Para("type")String type,
                               @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.win(deptId,userId,type,startTime,endTime));
    }
}
