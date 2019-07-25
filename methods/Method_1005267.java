/** 
 */
@RequestMapping(params="ruledategrid") public void ruledategrid(HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(TSDataRule.class,dataGrid);
  String functionId=oConvertUtils.getString(request.getParameter("functionId"));
  cq.eq("TSFunction.id",functionId);
  cq.add();
  this.systemService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
