/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(JformOrderMain2Entity jformOrderMain2,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(JformOrderMain2Entity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,jformOrderMain2);
  try {
    String sql=SuperQueryUtil.getComplxSuperQuerySQL(request);
    if (oConvertUtils.isNotEmpty(sql)) {
      cq.add(Restrictions.sqlRestriction(" id in (" + sql + ")"));
    }
  }
 catch (  Exception e) {
    throw new BusinessException(e.getMessage());
  }
  cq.add();
  this.jformOrderMain2Service.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
