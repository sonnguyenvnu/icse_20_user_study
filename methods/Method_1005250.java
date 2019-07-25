/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@SuppressWarnings("unchecked") @RequestMapping(params="datagrid") @ResponseBody public List<TreeGrid> datagrid(TSCategoryEntity category,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(TSCategoryEntity.class,dataGrid);
  if (category.getId() == null || StringUtils.isEmpty(category.getId())) {
    cq.isNull("parent");
  }
 else {
    cq.eq("parent.code",category.getId());
    category.setId(null);
  }
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,category,request.getParameterMap());
  List<TSCategoryEntity> list=this.categoryService.getListByCriteriaQuery(cq,false);
  List<TreeGrid> treeGrids=new ArrayList<TreeGrid>();
  TreeGridModel treeGridModel=new TreeGridModel();
  treeGridModel.setIdField("code");
  treeGridModel.setSrc("id");
  treeGridModel.setTextField("name");
  treeGridModel.setIcon("icon_iconPath");
  treeGridModel.setParentText("parent_name");
  treeGridModel.setParentId("parent_code");
  treeGridModel.setChildList("list");
  treeGrids=systemService.treegrid(list,treeGridModel);
  return treeGrids;
}
