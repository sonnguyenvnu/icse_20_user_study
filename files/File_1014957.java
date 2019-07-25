package com.kakarote.crm9.erp.crm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.kakarote.crm9.common.annotation.NotNullValidate;
import com.kakarote.crm9.common.annotation.Permissions;
import com.kakarote.crm9.common.config.paragetter.BasePageRequest;
import com.kakarote.crm9.erp.admin.entity.AdminRecord;
import com.kakarote.crm9.erp.admin.service.AdminSceneService;
import com.kakarote.crm9.erp.crm.common.CrmEnum;
import com.kakarote.crm9.erp.crm.entity.CrmContract;
import com.kakarote.crm9.erp.crm.entity.CrmContractProduct;
import com.kakarote.crm9.erp.crm.entity.CrmReceivables;
import com.kakarote.crm9.erp.crm.service.CrmContractService;
import com.kakarote.crm9.erp.crm.service.CrmReceivablesPlanService;
import com.kakarote.crm9.erp.crm.service.CrmReceivablesService;
import com.kakarote.crm9.utils.AuthUtil;
import com.kakarote.crm9.utils.R;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;


public class CrmContractController extends Controller {
    @Inject
    private CrmContractService crmContractService;
    @Inject
    private CrmReceivablesService receivablesService;
    @Inject
    private CrmReceivablesPlanService receivablesPlanService;

    @Inject
    private AdminSceneService adminSceneService;

    /**
     * @author wyq
     * æŸ¥çœ‹åˆ—è¡¨é¡µ
     */
    @Permissions({"crm:contract:index"})
    public void queryPageList(BasePageRequest basePageRequest){
        JSONObject jsonObject = basePageRequest.getJsonObject().fluentPut("type",6);
        basePageRequest.setJsonObject(jsonObject);
        renderJson(adminSceneService.filterConditionAndGetPageList(basePageRequest));
    }

    /**
     * åˆ†é¡µæ?¡ä»¶æŸ¥è¯¢å?ˆå?Œ
     * @author zxy
     */
    public void queryPage(BasePageRequest<CrmContract> basePageRequest){
        renderJson(R.ok().put("data",crmContractService.queryPage(basePageRequest)));
    }
    /**
     * æ ¹æ?®idæŸ¥è¯¢å?ˆå?Œ
     * @author zxy
     */
    @Permissions("crm:contract:read")
    @NotNullValidate(value = "contractId",message = "å?ˆå?Œidä¸?èƒ½ä¸ºç©º")
    public void queryById(@Para("contractId") Integer id){
        renderJson(crmContractService.queryById(id));
    }
    /**
     * æ ¹æ?®idåˆ é™¤å?ˆå?Œ
     * @author zxy
     */
    @Permissions("crm:contract:delete")
    @NotNullValidate(value = "contractIds",message = "å?ˆå?Œidä¸?èƒ½ä¸ºç©º")
    public void deleteByIds(@Para("contractIds") String contractIds){
        renderJson(crmContractService.deleteByIds(contractIds));
    }
    /**
     * @author wyq
     * å?ˆå?Œè½¬ç§»
     */
    @Permissions("crm:contract:transfer")
    @NotNullValidate(value = "contractIds",message = "å?ˆå?Œidä¸?èƒ½ä¸ºç©º")
    @NotNullValidate(value = "newOwnerUserId",message = "è´Ÿè´£äººidä¸?èƒ½ä¸ºç©º")
    @NotNullValidate(value = "transferType",message = "ç§»é™¤æ–¹å¼?ä¸?èƒ½ä¸ºç©º")
    public void transfer(@Para("")CrmContract crmContract){
        renderJson(crmContractService.transfer(crmContract));
    }
    /**
     * æ·»åŠ æˆ–ä¿®æ”¹
     * @author zxy
     */
    @Permissions({"crm:contract:save","crm:contract:update"})
    public void saveAndUpdate(){
        String data = getRawData();
        JSONObject jsonObject = JSON.parseObject(data);
        renderJson(crmContractService.saveAndUpdate(jsonObject));
    }
    /**
     * æ ¹æ?®æ?¡ä»¶æŸ¥è¯¢å?ˆå?Œ
     * @author zxy
     */
    public void queryList(@Para("")CrmContract crmContract){
        renderJson(R.ok().put("data",crmContractService.queryList(crmContract)));
    }
    /**
     * æ ¹æ?®æ?¡ä»¶æŸ¥è¯¢å?ˆå?Œ
     * @author zxy
     */
    @NotNullValidate(value = "id",message = "idä¸?èƒ½ä¸ºç©º")
    @NotNullValidate(value = "type",message = "ç±»åž‹ä¸?èƒ½ä¸ºç©º")
    public void queryListByType(@Para("type") String type,@Para("id")Integer id ){
        renderJson(R.ok().put("data",crmContractService.queryListByType(type,id)));
    }
    /**
     æ ¹æ?®å?ˆå?Œæ‰¹æ¬¡æŸ¥è¯¢äº§å“?
     * @param batchId
     * @author zxy
     */
    public void queryProductById(@Para("batchId") String batchId){
        renderJson(R.ok().put("data",crmContractService.queryProductById(batchId)));
    }
    /**
     * æ ¹æ?®å?ˆå?ŒidæŸ¥è¯¢å›žæ¬¾
     * @author zxy
     */
    public void queryReceivablesById(@Para("id") Integer id){
        renderJson(R.ok().put("data",crmContractService.queryReceivablesById(id)));
    }
    /**
     * æ ¹æ?®å?ˆå?ŒidæŸ¥è¯¢å›žæ¬¾è®¡åˆ’
     * @author zxy
     */
    public void queryReceivablesPlanById(@Para("id") Integer id){
        renderJson(R.ok().put("data",crmContractService.queryReceivablesPlanById(id)));
    }

    /**
     * @author wyq
     * æŸ¥è¯¢å›¢é˜Ÿæˆ?å‘˜
     */
    public void getMembers(@Para("contractId")Integer contractId){
        boolean auth = AuthUtil.isCrmAuth(AuthUtil.getCrmTablePara(CrmEnum.CONTRACT_TYPE_KEY.getSign()), contractId);
        if(auth){renderJson(R.noAuth()); return; }
        renderJson(R.ok().put("data",crmContractService.getMembers(contractId)));
    }

    /**
     * @author wyq
     * ç¼–è¾‘å›¢é˜Ÿæˆ?å‘˜
     */
    public void updateMembers(@Para("")CrmContract crmContract){
        renderJson(crmContractService.addMember(crmContract));
    }

    /**
     * @author wyq
     * æ·»åŠ å›¢é˜Ÿæˆ?å‘˜
     */
    @Permissions("crm:contract:teamsave")
    public void addMembers(@Para("")CrmContract crmContract){
        renderJson(crmContractService.addMember(crmContract));
    }

    /**
     * @author wyq
     * åˆ é™¤å›¢é˜Ÿæˆ?å‘˜
     */
    public void deleteMembers(@Para("")CrmContract crmContract){
        renderJson(crmContractService.deleteMembers(crmContract));
    }

    /**
     * æŸ¥è¯¢å?ˆå?Œè‡ªå®šä¹‰å­—æ®µ
     * @author zxy
     */
    public void queryField(){
        renderJson(R.ok().put("data",crmContractService.queryField()));
    }

    /**
     * @author wyq
     * æ·»åŠ è·Ÿè¿›è®°å½•
     */
    @NotNullValidate(value = "typesId",message = "å?ˆå?Œidä¸?èƒ½ä¸ºç©º")
    @NotNullValidate(value = "content",message = "å†…å®¹ä¸?èƒ½ä¸ºç©º")
    @NotNullValidate(value = "category",message = "è·Ÿè¿›ç±»åž‹ä¸?èƒ½ä¸ºç©º")
    public void addRecord(@Para("")AdminRecord adminRecord){
        boolean auth = AuthUtil.isCrmAuth(AuthUtil.getCrmTablePara(CrmEnum.CONTRACT_TYPE_KEY.getSign()), adminRecord.getTypesId());
        if(auth){renderJson(R.noAuth()); return; }
        renderJson(crmContractService.addRecord(adminRecord));
    }

    /**
     * @author wyq
     * æŸ¥çœ‹è·Ÿè¿›è®°å½•
     */
    public void getRecord(BasePageRequest<CrmContract> basePageRequest){
        boolean auth = AuthUtil.isCrmAuth(AuthUtil.getCrmTablePara(CrmEnum.CONTRACT_TYPE_KEY.getSign()), basePageRequest.getData().getContractId());
        if(auth){renderJson(R.noAuth()); return; }
        renderJson(R.ok().put("data",crmContractService.getRecord(basePageRequest)));
    }
    /**
     * æ ¹æ?®å?ˆå?ŒIDæŸ¥è¯¢å›žæ¬¾
     * @author zxy
     */
    public void qureyReceivablesListByContractId(BasePageRequest<CrmReceivables> basePageRequest){
        boolean auth = AuthUtil.isCrmAuth(AuthUtil.getCrmTablePara(CrmEnum.CONTRACT_TYPE_KEY.getSign()), basePageRequest.getData().getContractId());
        if(auth){renderJson(R.noAuth()); return; }
        renderJson(receivablesService.qureyListByContractId(basePageRequest));
    }
    /**
     * æ ¹æ?®å?ˆå?ŒIDæŸ¥è¯¢äº§å“?
     * @author zxy
     */
    public void qureyProductListByContractId(BasePageRequest<CrmContractProduct> basePageRequest){
        boolean auth = AuthUtil.isCrmAuth(AuthUtil.getCrmTablePara(CrmEnum.CONTRACT_TYPE_KEY.getSign()), basePageRequest.getData().getContractId());
        if(auth){renderJson(R.noAuth()); return; }
        renderJson(crmContractService.qureyProductListByContractId(basePageRequest));
    }
    /**
     * æ ¹æ?®å?ˆå?ŒIDæŸ¥è¯¢å›žæ¬¾è®¡åˆ’
     * @author zxy
     */
    public void qureyReceivablesPlanListByContractId(BasePageRequest<CrmReceivables> basePageRequest){
        boolean auth = AuthUtil.isCrmAuth(AuthUtil.getCrmTablePara(CrmEnum.CONTRACT_TYPE_KEY.getSign()), basePageRequest.getData().getContractId());
        if(auth){renderJson(R.noAuth()); return; }
        renderJson(receivablesPlanService.qureyListByContractId(basePageRequest));
    }

    /**
     * æŸ¥è¯¢å?ˆå?Œåˆ°æœŸæ??é†’è®¾ç½®
     */
    public void queryContractConfig(){
        renderJson(crmContractService.queryContractConfig());
    }

    /**
     * ä¿®æ”¹å?ˆå?Œåˆ°æœŸæ??é†’è®¾ç½®
     */
    @NotNullValidate(value = "status",message = "statusä¸?èƒ½ä¸ºç©º")
    @NotNullValidate(value = "contractDay",message = "contractDayä¸?èƒ½ä¸ºç©º")
    public void setContractConfig(@Para("status") Integer status,@Para("contractDay") Integer contractDay){
        renderJson(crmContractService.setContractConfig(status,contractDay));
    }
}
