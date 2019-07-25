/** 
 * @author wyq????
 */
public List<Record> information(Integer contactsId){
  Record record=Db.findFirst("select * from contactsview where contacts_id = ?",contactsId);
  if (null == record) {
    return null;
  }
  List<Record> fieldList=new ArrayList<>();
  FieldUtil field=new FieldUtil(fieldList);
  field.set("??",record.getStr("name")).set("????",record.getStr("customer_name")).set("??????",DateUtil.formatDateTime(record.get("next_time"))).set("??",record.getStr("post")).set("??",record.getStr("mobile")).set("??",record.getStr("telephone")).set("??",record.getStr("email")).set("??",record.getStr("address")).set("??",record.getStr("remark"));
  List<Record> recordList=Db.find("select name,value from 72crm_admin_fieldv where batch_id = ?",record.getStr("batch_id"));
  fieldList.addAll(recordList);
  return fieldList;
}
