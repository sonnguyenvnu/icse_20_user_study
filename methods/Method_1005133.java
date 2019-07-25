/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(JfromOrderEntity jfromOrder,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(JfromOrderEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,jfromOrder);
  try {
    String sql=SuperQueryUtil.getComplxSuperQuerySQL(request);
    if (oConvertUtils.isNotEmpty(sql)) {
      cq.add(Restrictions.sqlRestriction(" id in (" + sql + ")"));
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
    throw new BusinessException(e.getMessage());
  }
  cq.add();
  this.jfromOrderService.getDataGridReturn(cq,true);
  TagUtil.datagrid(response,dataGrid);
}
