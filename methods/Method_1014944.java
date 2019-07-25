/** 
 * @author wyq
 * @param types ????
 * @param id ??????
 */
public void information(@Para("types") Integer types,@Para("id") Integer id){
  List<Record> recordList;
  boolean auth=AuthUtil.isCrmAuth(AuthUtil.getCrmTablePara(CrmEnum.getSign(types)),id);
  if (auth) {
    renderJson(R.noAuth());
    return;
  }
  if (1 == types) {
    recordList=crmLeadsService.information(id);
  }
 else   if (2 == types) {
    recordList=crmCustomerService.information(id);
  }
 else   if (3 == types) {
    recordList=crmContactsService.information(id);
  }
 else   if (4 == types) {
    recordList=crmProductService.information(id);
  }
 else   if (5 == types) {
    recordList=crmBusinessService.information(id);
  }
 else   if (6 == types) {
    recordList=crmContractService.information(id);
  }
 else   if (7 == types) {
    recordList=crmReceivablesService.information(id);
  }
 else {
    recordList=new ArrayList<>();
  }
  renderJson(R.ok().put("data",recordList));
}
