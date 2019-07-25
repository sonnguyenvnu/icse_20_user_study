/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(JeecgDemoEntity jeecgDemo,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(JeecgDemoEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,jeecgDemo,request.getParameterMap());
  try {
  }
 catch (  Exception e) {
    throw new BusinessException(e.getMessage());
  }
  cq.add();
  this.jeecgDemoService.getDataGridReturn(cq,true);
  List<JeecgDemoEntity> list=dataGrid.getResults();
  Map<String,Map<String,Object>> extMap=new HashMap<String,Map<String,Object>>();
  for (  JeecgDemoEntity temp : list) {
    Map m=new HashMap();
    m.put("extField",this.jeecgMinidaoDao.getOrgCode(temp.getDepId()));
    extMap.put(temp.getId(),m);
  }
  dataGrid.setFooter("[{'salary':'','age':'','name':'??'}]");
  TagUtil.datagrid(response,dataGrid,extMap);
}
