/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(TSTimeTaskEntity timeTask,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(TSTimeTaskEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,timeTask,request.getParameterMap());
  this.timeTaskService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
