/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@SuppressWarnings("unchecked") @RequestMapping(params="datagrid") public void datagrid(CgformButtonSqlEntity cgformButtonSql,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(CgformButtonSqlEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,cgformButtonSql,request.getParameterMap());
  this.cgformButtonSqlService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
