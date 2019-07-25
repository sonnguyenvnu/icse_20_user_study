package com.kakarote.crm9.erp.crm.service;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kakarote.crm9.common.config.paragetter.BasePageRequest;
import com.kakarote.crm9.erp.admin.entity.AdminRecord;
import com.kakarote.crm9.erp.admin.entity.AdminUser;
import com.kakarote.crm9.erp.admin.service.AdminFieldService;
import com.kakarote.crm9.erp.admin.service.AdminFileService;
import com.kakarote.crm9.erp.crm.common.CrmEnum;
import com.kakarote.crm9.erp.crm.entity.*;
import com.kakarote.crm9.erp.oa.common.OaEnum;
import com.kakarote.crm9.erp.oa.entity.OaEvent;
import com.kakarote.crm9.erp.oa.entity.OaEventRelation;
import com.kakarote.crm9.erp.oa.service.OaActionRecordService;
import com.kakarote.crm9.utils.AuthUtil;
import com.kakarote.crm9.utils.BaseUtil;
import com.kakarote.crm9.utils.FieldUtil;
import com.kakarote.crm9.utils.R;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.plugin.activerecord.tx.Tx;

import java.util.*;

public class CrmBusinessService {
    @Inject
    private AdminFieldService adminFieldService;

    @Inject
    private FieldUtil fieldUtil;

    @Inject
    private CrmRecordService crmRecordService;

    @Inject
    private AdminFileService adminFileService;

    @Inject
    private OaActionRecordService oaActionRecordService;

    @Inject
    private AuthUtil authUtil;

    /**
     * @author wyq
     * 分页�?�件查询商机
     */
    public Page<Record> getBusinessPageList(BasePageRequest basePageRequest) {
        return Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(),new SqlPara().setSql("select * from businessview"));
    }

    /**
     * @author wyq
     * 新增或更新商机
     */
    @Before(Tx.class)
    public R addOrUpdate(JSONObject jsonObject) {
        CrmBusiness crmBusiness = jsonObject.getObject("entity", CrmBusiness.class);
        JSONArray jsonArray = jsonObject.getJSONArray("product");
        List<CrmBusinessProduct> businessProductList = jsonArray.toJavaList(CrmBusinessProduct.class);
        Db.delete(Db.getSql("crm.business.clearBusinessProduct"), crmBusiness.getBusinessId());
        String batchId = StrUtil.isNotEmpty(crmBusiness.getBatchId()) ? crmBusiness.getBatchId() : IdUtil.simpleUUID();
        crmRecordService.updateRecord(jsonObject.getJSONArray("field"), batchId);
        adminFieldService.save(jsonObject.getJSONArray("field"), batchId);
        boolean saveOrUpdate;
        if (crmBusiness.getBusinessId() != null) {
            crmBusiness.setUpdateTime(DateUtil.date());
            crmRecordService.updateRecord(new CrmBusiness().dao().findById(crmBusiness.getBusinessId()), crmBusiness, CrmEnum.BUSINESS_TYPE_KEY.getTypes());
            saveOrUpdate = crmBusiness.update();
        } else {
            crmBusiness.setCreateTime(DateUtil.date());
            crmBusiness.setUpdateTime(DateUtil.date());
            crmBusiness.setCreateUserId(BaseUtil.getUser().getUserId().intValue());
            crmBusiness.setOwnerUserId(BaseUtil.getUser().getUserId().intValue());
            crmBusiness.setBatchId(batchId);
            crmBusiness.setRwUserId(",");
            crmBusiness.setRoUserId(",");
            saveOrUpdate = crmBusiness.save();
            crmRecordService.addRecord(crmBusiness.getBusinessId(), CrmEnum.BUSINESS_TYPE_KEY.getTypes());
        }
        if (businessProductList != null) {
            for (CrmBusinessProduct crmBusinessProduct : businessProductList) {
                crmBusinessProduct.setBusinessId(crmBusiness.getBusinessId());
                crmBusinessProduct.save();
            }
        }
        return saveOrUpdate ? R.ok() : R.error();
    }

    /**
     * @author wyq
     * 根�?�商机id查询
     */
    public R queryById(Integer businessId) {
        if(!authUtil.dataAuth("business","business_id",businessId)){
            return R.ok().put("data",new Record().set("dataAuth",0));
        }
        return R.ok().put("data",CrmBusiness.dao.findFirst(Db.getSql("crm.business.queryById"), businessId));
    }

    /**
     * @author wyq
     * 基本信�?�
     */
    public List<Record> information(Integer busienssId) {
        Record record = Db.findFirst(Db.getSql("crm.business.queryById"), busienssId);
        if (null == record) {
            return null;
        }
        List<Record> fieldList = new ArrayList<>();
        FieldUtil field = new FieldUtil(fieldList);
        field.set("商机�??称", record.getStr("business_name")).set("商机状�?组", record.getStr("type_name")).set("商机阶段", record.getStr("status_name"))
                .set("预计�?交日期", DateUtil.formatDateTime(record.get("deal_date"))).set("客户�??称", record.getStr("customer_name"))
                .set("商机金�?", record.getStr("money")).set("备注", record.getStr("remark"));
        List<Record> recordList = Db.find("select name,value from 72crm_admin_fieldv where batch_id = ?",record.getStr("batch_id"));
        fieldList.addAll(recordList);
        return fieldList;
    }

    /**
     * @author wyq
     * 根�?�商机�??称查询
     */
    public Record queryByName(String name) {
        return Db.findFirst(Db.getSql("crm.business.queryByName"), name);
    }

    /**
     * @author wyq
     * 根�?�商机id查询产�?
     */
    public R queryProduct(BasePageRequest<CrmBusiness> basePageRequest) {
        Integer businessId = basePageRequest.getData().getBusinessId();
        Integer pageType = basePageRequest.getPageType();
        Record record = Db.findFirst(Db.getSql("crm.product.querySubtotalByBusinessId"), businessId);
        if (record.getStr("money") == null){
            record.set("money",0);
        }
        if (0 == pageType) {
            record.set("list", Db.find(Db.getSql("crm.business.queryProduct"), businessId));
        } else {
            record.set("list", Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), new SqlPara().setSql(Db.getSql("crm.business.queryProduct")).addPara(businessId)));
        }
        return R.ok().put("data", record);
    }

    /**
     * @author wyq
     * 根�?�商机id查询�?��?�
     */
    public R queryContract(BasePageRequest<CrmBusiness> basePageRequest) {
        Integer businessId = basePageRequest.getData().getBusinessId();
        Integer pageType = basePageRequest.getPageType();
        if (0 == pageType) {
            return R.ok().put("data", Db.find(Db.getSql("crm.business.queryContract"), businessId));
        } else {
            return R.ok().put("data", Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), new SqlPara().setSql(Db.getSql("crm.business.queryContract")).addPara(businessId)));
        }
    }

    /**
     * @author wyq
     * 根�?�商机id查询�?�系人
     */
    public R queryContacts(BasePageRequest<CrmBusiness> basePageRequest){
        Integer businessId = basePageRequest.getData().getBusinessId();
        Integer pageType = basePageRequest.getPageType();
        if (0 == pageType) {
            return R.ok().put("data", Db.find(Db.getSql("crm.business.queryContacts"), businessId));
        } else {
            return R.ok().put("data", Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), new SqlPara().setSql(Db.getSql("crm.business.queryContacts")).addPara(businessId)));
        }
    }

    /**
     * @author wyq
     * 商机关�?��?�系人
     */
    public R relateContacts(Integer businessId, String contactsIds){
        String[] contactsIdsArr = contactsIds.split(",");
        Db.delete("delete from 72crm_crm_contacts_business where business_id = ?",businessId);
        List<CrmContactsBusiness> crmContactsBusinessList = new ArrayList<>();
        for (String id:contactsIdsArr){
            CrmContactsBusiness crmContactsBusiness = new CrmContactsBusiness();
            crmContactsBusiness.setContactsId(Integer.valueOf(id));
            crmContactsBusiness.setBusinessId(businessId);
            crmContactsBusinessList.add(crmContactsBusiness);
        }
        Db.batchSave(crmContactsBusinessList,100);
        return R.ok();
    }

    /**
     * @author wyq
     * 商机解除关�?��?�系人
     */
    public R unrelateContacts(Integer businessId, String contactsIds){
        Db.delete("delete from 72crm_crm_contacts_business where business_id = ? and contacts_id in ("+contactsIds+")",businessId);
        return R.ok();
    }

    /**
     * @author wyq
     * 根�?�id删除商机
     */
    public R deleteByIds(String businessIds) {
        String[] idsArr = businessIds.split(",");
        Integer number = Db.queryInt(Db.getSql("crm.business.queryContractNumber"), businessIds);
        if (number > 0) {
            return R.error("该�?�数�?�与其他数�?�有必�?关�?�，请勿删除");
        }
        List<Record> idsList = new ArrayList<>();
        for (String id : idsArr) {
            Record record = new Record();
            idsList.add(record.set("business_id", Integer.valueOf(id)));
        }
        List<String> batchIdList = Db.query("select batch_id from 72crm_crm_business where business_id in ("+businessIds+")");
        return Db.tx(() -> {
            Db.batch(Db.getSql("crm.business.deleteByIds"), "business_id", idsList, 100);
            Db.batch("delete from 72crm_admin_fieldv where batch_id = ?","batch_id",batchIdList,100);
            return true;
        }) ? R.ok() : R.error();
    }

    /**
     * 根�?�客户id�?�更负责人
     *
     * @author wyq
     */
    public R updateOwnerUserId(CrmCustomer crmCustomer) {
        CrmBusiness crmBusiness = new CrmBusiness();
        crmBusiness.setNewOwnerUserId(crmCustomer.getNewOwnerUserId());
        crmBusiness.setTransferType(crmCustomer.getTransferType());
        crmBusiness.setPower(crmCustomer.getPower());
        String businessIds = Db.queryStr("select GROUP_CONCAT(business_id) from 72crm_crm_business where customer_id in ("+crmCustomer.getCustomerIds()+")");
        if (StrUtil.isEmpty(businessIds)){
            return R.ok();
        }
        crmBusiness.setBusinessIds(businessIds);
        return transfer(crmBusiness);
    }

    /**
     * @author wyq
     * 根�?�商机id�?�更负责人
     */
    public R transfer(CrmBusiness crmBusiness) {
        String[] businessIdsArr = crmBusiness.getBusinessIds().split(",");
        return Db.tx(() -> {
            for (String businessId : businessIdsArr) {
                String memberId = "," + crmBusiness.getNewOwnerUserId() + ",";
                Db.update(Db.getSql("crm.business.deleteMember"), memberId, memberId, Integer.valueOf(businessId));
                CrmBusiness oldBusiness = CrmBusiness.dao.findById(Integer.valueOf(businessId));
                if (2 == crmBusiness.getTransferType()) {
                    if (1 == crmBusiness.getPower()) {
                        crmBusiness.setRoUserId(oldBusiness.getRoUserId() + oldBusiness.getOwnerUserId() + ",");
                    }
                    if (2 == crmBusiness.getPower()) {
                        crmBusiness.setRwUserId(oldBusiness.getRwUserId() + oldBusiness.getOwnerUserId() + ",");
                    }
                }
                crmBusiness.setBusinessId(Integer.valueOf(businessId));
                crmBusiness.setOwnerUserId(crmBusiness.getNewOwnerUserId());
                crmBusiness.update();
                crmRecordService.addConversionRecord(Integer.valueOf(businessId), CrmEnum.BUSINESS_TYPE_KEY.getTypes(), crmBusiness.getNewOwnerUserId());
            }
            return true;
        }) ? R.ok() : R.error();
    }

    /**
     * @author wyq
     * 查询团队�?员
     */
    public List<Record> getMembers(Integer businessId) {
        CrmBusiness crmBusiness = CrmBusiness.dao.findById(businessId);
        List<Record> recordList = new ArrayList<>();
        if (crmBusiness.getOwnerUserId() != null) {
            Record ownerUser = Db.findFirst(Db.getSql("crm.customer.getMembers"), crmBusiness.getOwnerUserId());
            recordList.add(ownerUser.set("power", "负责人�?��?").set("groupRole", "负责人"));
        }
        String roUserId = crmBusiness.getRoUserId();
        String rwUserId = crmBusiness.getRwUserId();
        String memberIds = roUserId + rwUserId.substring(1);
        if (",".equals(memberIds)) {
            return recordList;
        }
        String[] memberIdsArr = memberIds.substring(1, memberIds.length() - 1).split(",");
        Set<String> memberIdsSet = new HashSet<>(Arrays.asList(memberIdsArr));
        for (String memberId : memberIdsSet) {
            Record record = Db.findFirst(Db.getSql("crm.customer.getMembers"), memberId);
            if (roUserId.contains(memberId)) {
                record.set("power", "�?�读").set("groupRole", "普通�?员");
            }
            if (rwUserId.contains(memberId)) {
                record.set("power", "读写").set("groupRole", "普通�?员");
            }
            recordList.add(record);
        }
        return recordList;
    }

    /**
     * @author wyq
     * 添加团队�?员
     */
    @Before(Tx.class)
    public R addMember(CrmBusiness crmBusiness) {
        String[] businessIdsArr = crmBusiness.getIds().split(",");
        String[] memberArr = crmBusiness.getMemberIds().split(",");
        StringBuffer stringBuffer = new StringBuffer();
        for (String id : businessIdsArr) {
            if (StrUtil.isNotEmpty(id)) {
                Integer ownerUserId = CrmBusiness.dao.findById(Integer.valueOf(id)).getOwnerUserId();
                for (String memberId : memberArr) {
                    if (ownerUserId.equals(Integer.valueOf(memberId))) {
                        return R.error("负责人�?能�?�?选为团队�?员");
                    }
                    Db.update(Db.getSql("crm.business.deleteMember"), "," + memberId + ",", "," + memberId + ",", Integer.valueOf(id));
                }
                if (1 == crmBusiness.getPower()) {
                    stringBuffer.setLength(0);
                    String roUserId = stringBuffer.append(CrmBusiness.dao.findById(Integer.valueOf(id)).getRoUserId()).append(crmBusiness.getMemberIds()).append(",").toString();
                    Db.update("update 72crm_crm_business set ro_user_id = ? where business_id = ?", roUserId, Integer.valueOf(id));
                }
                if (2 == crmBusiness.getPower()) {
                    stringBuffer.setLength(0);
                    String rwUserId = stringBuffer.append(CrmBusiness.dao.findById(Integer.valueOf(id)).getRwUserId()).append(crmBusiness.getMemberIds()).append(",").toString();
                    Db.update("update 72crm_crm_business set rw_user_id = ? where business_id = ?", rwUserId, Integer.valueOf(id));
                }
            }

        }
        return R.ok();
    }

    /**
     * @author wyq
     * 删除团队�?员
     */
    public R deleteMembers(CrmBusiness crmBusiness) {
        String[] businessIdsArr = crmBusiness.getIds().split(",");
        String[] memberArr = crmBusiness.getMemberIds().split(",");
        return Db.tx(() -> {
            for (String id : businessIdsArr) {
                for (String memberId : memberArr) {
                    Db.update(Db.getSql("crm.business.deleteMember"), "," + memberId + ",", "," + memberId + ",", Integer.valueOf(id));
                }
            }
            return true;
        }) ? R.ok() : R.error();
    }

    /**
     * @author wyq
     * 商机状�?组展示
     */
    public List<Record> queryBusinessStatus(Integer businessId) {
        return Db.find(Db.getSql("crm.business.queryBusinessStatus"), businessId);
    }

    /**
     * @author wyq
     * 商机状�?组推进
     */
    @Before(Tx.class)
    public R boostBusinessStatus(CrmBusiness crmBusiness) {
        if (crmBusiness.getStatusId() != null) {
            CrmBusinessChange change = new CrmBusinessChange();
            change.setBusinessId(crmBusiness.getBusinessId());
            change.setStatusId(crmBusiness.getStatusId());
            change.setCreateTime(DateUtil.date());
            change.setCreateUserId(BaseUtil.getUserId().intValue());
            change.save();
        }
        return crmBusiness.update() ? R.ok() : R.error();
    }

    /**
     * @author wyq
     * 查询新增字段
     */
    public List<Record> queryField() {
        List<Record> fieldList = new LinkedList<>();
        String[] settingArr = new String[]{};
        fieldUtil.getFixedField(fieldList, "businessName", "商机�??称", "", "text", settingArr, 1);
        fieldUtil.getFixedField(fieldList, "customerId", "客户�??称", "", "customer", settingArr, 1);
        fieldUtil.getFixedField(fieldList, "typeId", "商机状�?组", "", "business_type", settingArr, 1);
        fieldUtil.getFixedField(fieldList, "statusId", "商机阶段", "", "business_status", settingArr, 1);
        fieldUtil.getFixedField(fieldList, "money", "商机金�?", "", "floatnumber", settingArr, 0);
        fieldUtil.getFixedField(fieldList, "dealDate", "预计�?交日期", "", "datetime", settingArr, 1);
        fieldUtil.getFixedField(fieldList, "remark", "备注", "", "text", settingArr, 0);
        fieldUtil.getFixedField(fieldList, "product", "产�?", Kv.by("discount_rate", "").set("product", new ArrayList<>()).set("total_price", ""), "product", settingArr, 0);
        fieldList.addAll(adminFieldService.list("5"));
        return fieldList;
    }

    /**
     * @author wyq
     * 查询编辑字段
     */
    public List<Record> queryField(Integer businessId) {
        Record business = Db.findFirst("select * from businessview where business_id = ?",businessId);
        List<Record> customerList = new ArrayList<>();
        Record customer = new Record();
        customerList.add(customer.set("customer_id",business.getInt("customer_id")).set("customer_name",business.getStr("customer_name")));
        business.set("customer_id",customerList);
        List<Record> fieldList = adminFieldService.queryUpdateField(5,business);
        fieldList.add(new Record().set("field_name","type_id").set("name","商机状�?组").set("value",business.getInt("type_id")).set("form_type","business_type").set("setting",new String[0]).set("is_null",1).set("field_type",1));
        fieldList.add(new Record().set("field_name","status_id").set("name","商机阶段").set("value",business.getInt("status_id")).set("form_type","business_status").set("setting",new String[0]).set("is_null",1).set("field_type",1));
        Record totalPrice = Db.findFirst("select IFNULL(SUM(subtotal),0) as total_price from 72crm_crm_business_product where business_id = ?", businessId);
        List<Record> productList = Db.find(Db.getSql("crm.business.queryBusinessProduct"), businessId);
        Kv kv = Kv.by("discount_rate", customer.getBigDecimal("discount_rate")).set("product", productList).set("total_price", totalPrice.getStr("total_price"));
        fieldList.add(new Record().set("field_name","product").set("name","产�?").set("value",kv).set("setting",new String[]{}).set("is_null",0).set("field_type",1));
        return fieldList;
    }

    /**
     * @author wyq
     * 查询编辑字段
     */
//    public List<Record> queryField(Integer businessId) {
//        List<Record> fieldList = new LinkedList<>();
//        Record record = Db.findFirst("select * from businessview where business_id = ?", businessId);
//        String[] settingArr = new String[]{};
//        fieldUtil.getFixedField(fieldList, "business_name", "商机�??称", record.getStr("business_name"), "text", settingArr, 1);
//        List<Record> customerList = new ArrayList<>();
//        Record customer = new Record();
//        customerList.add(customer.set("customer_id", record.getInt("customer_id")).set("customer_name", record.getStr("customer_name")));
//        fieldUtil.getFixedField(fieldList, "customerId", "客户�??称", customerList, "customer", settingArr, 1);
//        fieldUtil.getFixedField(fieldList, "typeId", "商机状�?组", record.getInt("type_id"), "business_type", settingArr, 1);
//        fieldUtil.getFixedField(fieldList, "statusId", "商机阶段", record.getInt("status_id"), "business_status", settingArr, 1);
//        fieldUtil.getFixedField(fieldList, "money", "商机金�?", record.getStr("money"), "floatnumber", settingArr, 0);
//        fieldUtil.getFixedField(fieldList, "dealDate", "预计�?交日期", DateUtil.formatDateTime(record.get("deal_date")), "datetime", settingArr, 1);
//        fieldUtil.getFixedField(fieldList, "remark", "备注", record.getStr("remark"), "text", settingArr, 0);
//        fieldList.addAll(adminFieldService.queryByBatchId(record.getStr("batch_id")));
//        Record totalPrice = Db.findFirst("select IFNULL(SUM(subtotal),0) as total_price from 72crm_crm_business_product where business_id = ?", businessId);
//        List<Record> productList = Db.find(Db.getSql("crm.business.queryBusinessProduct"), businessId);
//        Kv kv = Kv.by("discount_rate", record.getBigDecimal("discount_rate")).set("product", productList).set("total_price", totalPrice.getStr("total_price"));
//        fieldUtil.getFixedField(fieldList, "product", "产�?", kv, "product", settingArr, 0);
//        return fieldList;
//    }

    /**
     * @author wyq
     * 查询商机状�?组�?�商机状�?
     */
    public List<Record> queryBusinessStatusOptions(String type) {
        List<Record> businessTypeList = Db.find("select * from 72crm_crm_business_type where status = 1");
        for (Record record : businessTypeList) {
            Integer typeId = record.getInt("type_id");
            List<Record> businessStatusList = Db.find("select * from 72crm_crm_business_status where type_id = ?", typeId);
            if ("condition".equals(type)){
                Record win = new Record();
                win.set("name","赢�?�").set("typeId",typeId).set("statusId","win");
                businessStatusList.add(win);
                Record lose = new Record();
                lose.set("name","输�?�").set("typeId",typeId).set("statusId","lose");
                businessStatusList.add(lose);
                Record invalid = new Record();
                invalid.set("name","无效").set("typeId",typeId).set("statusId","invalid");
                businessStatusList.add(invalid);
            }
            record.set("statusList", businessStatusList);
        }
        return businessTypeList;
    }

    /**
     * @author wyq
     * 添加跟进记录
     */
    @Before(Tx.class)
    public R addRecord(AdminRecord adminRecord) {
        adminRecord.setTypes("crm_business");
        adminRecord.setCreateTime(DateUtil.date());
        adminRecord.setCreateUserId(BaseUtil.getUser().getUserId().intValue());
        if (1 == adminRecord.getIsEvent()) {
            OaEvent oaEvent = new OaEvent();
            oaEvent.setTitle(adminRecord.getContent());
            oaEvent.setStartTime(adminRecord.getNextTime());
            oaEvent.setEndTime(DateUtil.offsetDay(adminRecord.getNextTime(), 1));
            oaEvent.setCreateTime(DateUtil.date());
            oaEvent.setCreateUserId(BaseUtil.getUser().getUserId().intValue());
            oaEvent.save();
            AdminUser user = BaseUtil.getUser();
            oaActionRecordService.addRecord(oaEvent.getEventId(), OaEnum.EVENT_TYPE_KEY.getTypes(),1,oaActionRecordService.getJoinIds(user.getUserId().intValue(),oaEvent.getOwnerUserIds()),oaActionRecordService.getJoinIds(user.getDeptId(),""));
            OaEventRelation oaEventRelation = new OaEventRelation();
            oaEventRelation.setEventId(oaEvent.getEventId());
            oaEventRelation.setBusinessIds(","+adminRecord.getTypesId().toString()+",");
            oaEventRelation.setCreateTime(DateUtil.date());
            oaEventRelation.save();
        }
        return adminRecord.save() ? R.ok() : R.error();
    }

    /**
     * @author wyq
     * 查看跟进记录
     */
    public List<Record> getRecord(BasePageRequest<CrmBusiness> basePageRequest) {
        CrmBusiness crmBusiness = basePageRequest.getData();
        List<Record> recordList = Db.find(Db.getSql("crm.business.getRecord"), crmBusiness.getBusinessId(),crmBusiness.getBusinessId());
        recordList.forEach(record -> {
            adminFileService.queryByBatchId(record.getStr("batch_id"), record);
            String businessIds = record.getStr("business_ids");
            List<CrmBusiness> businessList = new ArrayList<>();
            if (businessIds != null) {
                String[] businessIdsArr = businessIds.split(",");
                for (String businessId : businessIdsArr) {
                    businessList.add(CrmBusiness.dao.findById(Integer.valueOf(businessId)));
                }
            }
            String contactsIds = record.getStr("contacts_ids");
            List<CrmContacts> contactsList = new ArrayList<>();
            if (contactsIds != null) {
                String[] contactsIdsArr = contactsIds.split(",");
                for (String contactsId : contactsIdsArr) {
                    contactsList.add(CrmContacts.dao.findById(Integer.valueOf(contactsId)));
                }
            }
            record.set("business_list", businessList).set("contacts_list", contactsList);
        });
        return recordList;
    }
}
