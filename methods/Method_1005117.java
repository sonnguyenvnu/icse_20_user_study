/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(JformOrderCustomer2Entity jformOrderCustomer2,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(JformOrderCustomer2Entity.class,dataGrid);
  String mainId=request.getParameter("mainId");
  if (oConvertUtils.isNotEmpty(mainId)) {
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,jformOrderCustomer2,request.getParameterMap());
    try {
      cq.eq("fkId",mainId);
      String query_money_begin=request.getParameter("money_begin");
      String query_money_end=request.getParameter("money_end");
      if (StringUtil.isNotEmpty(query_money_begin)) {
        cq.ge("money",Double.parseDouble(query_money_begin));
      }
      if (StringUtil.isNotEmpty(query_money_end)) {
        cq.le("money",Double.parseDouble(query_money_end));
      }
    }
 catch (    Exception e) {
      throw new BusinessException(e.getMessage());
    }
    cq.add();
    this.jformOrderMain2Service.getDataGridReturn(cq,true);
  }
  TagUtil.datagrid(response,dataGrid);
}
