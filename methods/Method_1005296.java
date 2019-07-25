/** 
 * easyuiAJAX????
 * @param request
 * @param response
 * @param dataGrid
 */
@RequestMapping(params="datagrid") public void datagrid(HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(TSFunction.class,dataGrid);
  this.systemService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
