/** 
 * ??????????? 
 */
@RequestMapping(params="ruledatagrid") public void ruledategrid(HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(TSInterfaceDdataRuleEntity.class,dataGrid);
  String interfaceId=oConvertUtils.getString(request.getParameter("interfaceId"));
  cq.eq("TSInterface.id",interfaceId);
  cq.add();
  this.systemService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
