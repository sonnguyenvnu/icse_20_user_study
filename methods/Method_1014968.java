/** 
 * ??id????????
 */
public List<Record> information(Integer id){
  Record record=Db.findFirst("select * from productview where product_id = ?",id);
  if (record == null) {
    return null;
  }
  List<Record> fieldList=new ArrayList<>();
  FieldUtil field=new FieldUtil(fieldList);
  field.set("????",record.getStr("name")).set("????",record.getStr("category_name")).set("????",record.getStr("num")).set("????",record.getStr("price")).set("????",record.getStr("description"));
  List<Record> recordList=Db.find("select name,value from 72crm_admin_fieldv where batch_id = ?",record.getStr("batch_id"));
  fieldList.addAll(recordList);
  return fieldList;
}
