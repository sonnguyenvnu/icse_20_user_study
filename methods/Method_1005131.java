/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(JformOrderTicket2Entity.class,dataGrid);
  String mainId=request.getParameter("mainId");
  if (oConvertUtils.isNotEmpty(mainId)) {
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,jformOrderTicket2,request.getParameterMap());
    try {
      cq.eq("fckId",mainId);
    }
 catch (    Exception e) {
      throw new BusinessException(e.getMessage());
    }
    cq.add();
    this.jformOrderMain2Service.getDataGridReturn(cq,true);
  }
  TagUtil.datagrid(response,dataGrid);
}
