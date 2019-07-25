/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(CgreportConfigHeadEntity cgreportConfigHead,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(CgreportConfigHeadEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,cgreportConfigHead);
  try {
  }
 catch (  Exception e) {
    throw new BusinessException(e.getMessage());
  }
  cq.add();
  this.cgreportConfigHeadService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
