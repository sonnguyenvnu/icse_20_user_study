/** 
 * easyuiAJAX????
 * @param request
 * @param response
 * @param dataGrid
 */
@RequestMapping(params="opdategrid") public void opdategrid(HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(TSOperation.class,dataGrid);
  String functionId=oConvertUtils.getString(request.getParameter("functionId"));
  cq.eq("TSFunction.id",functionId);
  cq.add();
  this.systemService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
