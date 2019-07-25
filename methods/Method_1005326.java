/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(TSSmsSqlEntity tSSmsSql,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(TSSmsSqlEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,tSSmsSql,request.getParameterMap());
  try {
  }
 catch (  Exception e) {
    throw new BusinessException(e.getMessage());
  }
  cq.add();
  this.systemService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
