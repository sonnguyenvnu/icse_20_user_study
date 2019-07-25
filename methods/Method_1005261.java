/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param
 */
@RequestMapping(params="datagrid") public void datagrid(DynamicDataSourceEntity dbSource,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(DynamicDataSourceEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,dbSource,request.getParameterMap());
  this.systemService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
