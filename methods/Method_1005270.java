/** 
 * easyuiAJAX????
 * @param request
 * @param response
 * @param dataGrid
 */
@SuppressWarnings("unchecked") @RequestMapping(params="datagrid") public void datagrid(TSIcon icon,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(TSIcon.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,icon);
  cq.add();
  this.systemService.getDataGridReturn(cq,true);
  IconImageUtil.convertDataGrid(dataGrid,request);
  List<TSIcon> list=dataGrid.getResults();
  for (  TSIcon tsicon : list) {
    tsicon.setIconName(MutiLangUtil.doMutiLang(tsicon.getIconName(),""));
  }
  TagUtil.datagrid(response,dataGrid);
}
