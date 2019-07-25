/** 
 * @author wyq????
 */
public List<Record> information(Integer leadsId){
  CrmLeads crmLeads=CrmLeads.dao.findById(leadsId);
  List<Record> fieldList=new ArrayList<>();
  FieldUtil field=new FieldUtil(fieldList);
  field.set("????",crmLeads.getLeadsName()).set("??",crmLeads.getMobile()).set("??",crmLeads.getTelephone()).set("??????",DateUtil.formatDateTime(crmLeads.getNextTime())).set("??",crmLeads.getAddress()).set("??",crmLeads.getRemark());
  List<Record> recordList=Db.find("select name,value from 72crm_admin_fieldv where batch_id = ?",crmLeads.getBatchId());
  fieldList.addAll(recordList);
  return fieldList;
}
