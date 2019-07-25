/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 */
@RequestMapping(params="datagrid") public void datagrid(MutiLangEntity mutiLang,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(MutiLangEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,mutiLang,request.getParameterMap());
  this.systemService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
