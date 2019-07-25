package com.kakarote.crm9.erp.bi.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.kakarote.crm9.erp.bi.service.BiRankingService;

public class BiRankingController extends Controller {
    @Inject
    private BiRankingService service;
    /**
     * �?��?�金�?排行榜
     * @author zxy
     */
    public void contractRanKing(@Para("deptId")Integer deptId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.contractRanKing(deptId,type,startTime,endTime));
    }
    /**
     * 回款金�?排行榜
     * @author zxy
     */
    public void receivablesRanKing(@Para("deptId")Integer deptId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.receivablesRanKing(deptId,type,startTime,endTime));
    }
    /**
     * 签约�?��?�排行榜
     * @author zxy
     */
    public void contractCountRanKing(@Para("deptId")Integer deptId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.contractCountRanKing(deptId,type,startTime,endTime));
    }
    /**
     * 产�?销�?排行榜
     * @author zxy
     */
    public void productCountRanKing(@Para("deptId")Integer deptId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.productCountRanKing(deptId,type,startTime,endTime));
    }
    /**
     * 新增客户数排行榜
     * @author zxy
     */
    public void customerCountRanKing(@Para("deptId")Integer deptId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.customerCountRanKing(deptId,type,startTime,endTime));
    }
    /**
     * 新增�?�系人排行榜
     * @author zxy
     */
    public void contactsCountRanKing(@Para("deptId")Integer deptId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.contactsCountRanKing(deptId,type,startTime,endTime));
    }
    /**
     * 跟进客户数排行榜
     * @author zxy
     */
    public void customerGenjinCountRanKing(@Para("deptId")Integer deptId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.customerGenjinCountRanKing(deptId,type,startTime,endTime));
    }
    /**
     * 跟进次数排行榜
     * @author zxy
     */
    public void recordCountRanKing(@Para("deptId")Integer deptId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.recordCountRanKing(deptId,type,startTime,endTime));
    }
    /**
     * 产�?分类销�?分�?
     * @author zxy
     */
    public void contractProductRanKing(@Para("deptId")Integer deptId,@Para("userId")Long userId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.contractProductRanKing(deptId,userId,type,startTime,endTime));
    }
    /**
     * 出差次数排行
     * @author zxy
     */
    public void travelCountRanKing(@Para("deptId")Integer deptId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.travelCountRanKing(deptId,type,startTime,endTime));
    }
    /**
     * 产�?销售情况统计
     * @author zxy
     */
    public void productSellRanKing(@Para("deptId")Integer deptId,@Para("userId")Long userId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.productSellRanKing(deptId,userId,type,startTime,endTime));
    }
    /**
     * 城市分布分�?
     * @author zxy
     */
    public void addressAnalyse(){
        renderJson(service.addressAnalyse());
    }
    /**
     * 客户行业分�?
     * @author zxy
     */
    public void portrait(@Para("deptId")Integer deptId,@Para("userId")Long userId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.portrait(deptId,userId,type,startTime,endTime));
    }
    /**
     * 客户级别分�?
     * @author zxy
     */
    public void portraitLevel(@Para("deptId")Integer deptId,@Para("userId")Long userId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.portraitLevel(deptId,userId,type,startTime,endTime));
    }
    /**
     * 客户级别分�?
     * @author zxy
     */
    public void portraitSource(@Para("deptId")Integer deptId,@Para("userId")Long userId, @Para("type")String type, @Para("startTime") String startTime, @Para("endTime")String endTime){
        renderJson(service.portraitSource(deptId,userId,type,startTime,endTime));
    }
}
