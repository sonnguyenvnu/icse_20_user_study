/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@SuppressWarnings("unchecked") @RequestMapping(params="datagrid") public void datagrid(CgformButtonEntity cgformButton,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(CgformButtonEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,cgformButton,request.getParameterMap());
  this.cgformButtonService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
