/** 
 * ??id????????
 */
public List<Record> information(Integer id){
  Record record=Db.findFirst(Db.getSql("crm.contract.queryByContractId"),id);
  if (record == null) {
    return null;
  }
  List<Record> fieldList=new ArrayList<>();
  FieldUtil field=new FieldUtil(fieldList);
  field.set("????",record.getStr("num")).set("????",record.getStr("name")).set("????",record.getStr("customer_name")).set("????",record.getStr("business_name")).set("????",DateUtil.formatDate(record.getDate("order_date"))).set("????",record.getStr("money")).set("??????",DateUtil.formatDate(record.getDate("start_time"))).set("??????",DateUtil.formatDate(record.getDate("end_time"))).set("?????",record.getStr("contacts_name")).set("?????",record.getStr("company_user_name")).set("??",record.getStr("remark"));
  List<Record> recordList=Db.find("select name,value from 72crm_admin_fieldv where batch_id = ?",record.getStr("batch_id"));
  fieldList.addAll(recordList);
  return fieldList;
}
