/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(TsBlackListEntity tsBlackList,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(TsBlackListEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,tsBlackList,request.getParameterMap());
  try {
  }
 catch (  Exception e) {
    throw new BusinessException(e.getMessage());
  }
  cq.add();
  this.tsBlackListService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
