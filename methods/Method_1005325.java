/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="mydatagrid") public void mydatagrid(TSSmsEntity tSSms,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(TSSmsEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,tSSms,request.getParameterMap());
  String curUser=ResourceUtil.getSessionUser().getUserName();
  try {
    cq.eq("esType","3");
    cq.eq("esReceiver",curUser);
    cq.addOrder("esSendtime",SortDirection.desc);
  }
 catch (  Exception e) {
    throw new BusinessException(e.getMessage());
  }
  cq.add();
  this.tSSmsService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
