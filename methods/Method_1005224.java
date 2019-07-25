/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="datagrid") public void datagrid(CgFormHeadEntity cgFormHead,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(CgFormHeadEntity.class,dataGrid);
  String jformCategory=request.getParameter("jformCategory");
  if (StringUtil.isNotEmpty(jformCategory)) {
    cq.eq("jformCategory",jformCategory);
  }
  cq.isNull("physiceId");
  cq.add();
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,cgFormHead);
  this.cgFormFieldService.getDataGridReturn(cq,true);
  List<CgFormHeadEntity> list=dataGrid.getResults();
  Map<String,Map<String,Object>> extMap=new HashMap<String,Map<String,Object>>();
  List<Map<String,Object>> pzlist=this.cgFormFieldService.getPeizhiCountByIds(list);
  for (  Map<String,Object> temp : pzlist) {
    Map<String,Object> m=new HashMap<String,Object>();
    m.put("hasPeizhi",temp.get("hasPeizhi") == null ? "0" : temp.get("hasPeizhi"));
    extMap.put(temp.get("id").toString(),m);
  }
  for (  CgFormHeadEntity temp : list) {
    if (extMap.get(temp.getId()) == null) {
      Map<String,Object> m=new HashMap<String,Object>();
      m.put("hasPeizhi","0");
      extMap.put(temp.getId(),m);
    }
  }
  TagUtil.datagrid(response,dataGrid,extMap);
}
