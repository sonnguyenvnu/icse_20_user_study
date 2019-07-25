/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(JeecgDemoExcelEntity jeecgDemoExcel,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(JeecgDemoExcelEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,jeecgDemoExcel,request.getParameterMap());
  try {
  }
 catch (  Exception e) {
    throw new BusinessException(e.getMessage());
  }
  cq.add();
  this.jeecgDemoExcelService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
