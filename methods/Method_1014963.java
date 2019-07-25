/** 
 * @author wyq????
 */
public List<Record> information(Integer busienssId){
  Record record=Db.findFirst(Db.getSql("crm.business.queryById"),busienssId);
  if (null == record) {
    return null;
  }
  List<Record> fieldList=new ArrayList<>();
  FieldUtil field=new FieldUtil(fieldList);
  field.set("????",record.getStr("business_name")).set("?????",record.getStr("type_name")).set("????",record.getStr("status_name")).set("??????",DateUtil.formatDateTime(record.get("deal_date"))).set("????",record.getStr("customer_name")).set("????",record.getStr("money")).set("??",record.getStr("remark"));
  List<Record> recordList=Db.find("select name,value from 72crm_admin_fieldv where batch_id = ?",record.getStr("batch_id"));
  fieldList.addAll(recordList);
  return fieldList;
}
