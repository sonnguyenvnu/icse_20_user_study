/** 
 * @author wyq????
 */
public List<Record> information(Integer customerId){
  CrmCustomer crmCustomer=CrmCustomer.dao.findById(customerId);
  List<Record> fieldList=new ArrayList<>();
  FieldUtil field=new FieldUtil(fieldList);
  field.set("????",crmCustomer.getCustomerName()).set("????",crmCustomer.getDealStatus()).set("??????",DateUtil.formatDateTime(crmCustomer.getNextTime())).set("??",crmCustomer.getWebsite()).set("??",crmCustomer.getRemark()).set("??",crmCustomer.getTelephone()).set("??",crmCustomer.getMobile()).set("??",crmCustomer.getLocation()).set("??",crmCustomer.getAddress()).set("????",crmCustomer.getDetailAddress());
  List<Record> recordList=Db.find("select a.name,a.value,b.type from 72crm_admin_fieldv as a left join 72crm_admin_field as b on a.field_id = b.field_id where batch_id = ?",crmCustomer.getBatchId());
  recordList.forEach(record -> {
    if (record.getInt("type") == 8) {
      record.set("value",Db.query("select name from 72crm_admin_file where batch_id = ?",record.getStr("value")));
    }
  }
);
  fieldList.addAll(recordList);
  return fieldList;
}
