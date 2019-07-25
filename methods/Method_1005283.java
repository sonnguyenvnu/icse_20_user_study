/** 
 * ?????????
 * @param request
 * @param response
 * @param treegrid
 * @return
 */
@RequestMapping(params="departgrid") @ResponseBody public Object departgrid(TSDepart tSDepart,HttpServletRequest request,HttpServletResponse response,TreeGrid treegrid){
  CriteriaQuery cq=new CriteriaQuery(TSDepart.class);
  if ("yes".equals(request.getParameter("isSearch"))) {
    treegrid.setId(null);
    tSDepart.setId(null);
  }
  if (null != tSDepart.getDepartname()) {
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,tSDepart);
  }
  if (treegrid.getId() != null) {
    cq.eq("TSPDepart.id",treegrid.getId());
  }
  if (treegrid.getId() == null) {
    cq.isNull("TSPDepart");
  }
  cq.addOrder("orgCode",SortDirection.asc);
  cq.add();
  List<TreeGrid> departList=null;
  departList=systemService.getListByCriteriaQuery(cq,false);
  if (departList.size() == 0 && tSDepart.getDepartname() != null) {
    cq=new CriteriaQuery(TSDepart.class);
    TSDepart parDepart=new TSDepart();
    tSDepart.setTSPDepart(parDepart);
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,tSDepart);
    departList=systemService.getListByCriteriaQuery(cq,false);
  }
  List<TreeGrid> treeGrids=new ArrayList<TreeGrid>();
  TreeGridModel treeGridModel=new TreeGridModel();
  treeGridModel.setTextField("departname");
  treeGridModel.setParentText("TSPDepart_departname");
  treeGridModel.setParentId("TSPDepart_id");
  treeGridModel.setSrc("description");
  treeGridModel.setIdField("id");
  treeGridModel.setChildList("TSDeparts");
  Map<String,Object> fieldMap=new HashMap<String,Object>();
  fieldMap.put("orgCode","orgCode");
  fieldMap.put("orgType","orgType");
  fieldMap.put("mobile","mobile");
  fieldMap.put("fax","fax");
  fieldMap.put("address","address");
  fieldMap.put("order","departOrder");
  treeGridModel.setFieldMap(fieldMap);
  treeGrids=systemService.treegrid(departList,treeGridModel);
  JSONArray jsonArray=new JSONArray();
  for (  TreeGrid treeGrid : treeGrids) {
    jsonArray.add(JSON.parse(treeGrid.toJson()));
  }
  return jsonArray;
}
