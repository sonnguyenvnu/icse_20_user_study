/** 
 * ??id????????
 */
public List<Record> information(Integer id){
  Record record=Db.findFirst(Db.getSqlPara("crm.receivables.queryReceivablesById",Kv.by("id",id)));
  if (record == null) {
    return null;
  }
  List<Record> fieldList=new ArrayList<>();
  FieldUtil field=new FieldUtil(fieldList);
  field.set("????",record.getStr("number")).set("????",record.getStr("customer_name")).set("????",record.getStr("contract_num")).set("????",DateUtil.formatDate(record.getDate("return_time"))).set("????",record.getStr("money")).set("??",record.getStr("plan_num")).set("??",record.getStr("remark"));
  List<Record> recordList=Db.find("select name,value from 72crm_admin_fieldv where batch_id = ?",record.getStr("batch_id"));
  fieldList.addAll(recordList);
  return fieldList;
}
