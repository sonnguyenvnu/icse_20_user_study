/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(JformOrderMainEntity jformOrderMain,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(JformOrderMainEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,jformOrderMain);
  try {
  }
 catch (  Exception e) {
    throw new BusinessException(e.getMessage());
  }
  cq.add();
  this.jformOrderMainService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
