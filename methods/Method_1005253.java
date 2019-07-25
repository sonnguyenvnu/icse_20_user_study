/** 
 * ??????,???????????????
 * @param comboTree
 * @return
 */
@RequestMapping(params="tree") @ResponseBody public List<ComboTree> tree(String selfCode,ComboTree comboTree,boolean isNew){
  CriteriaQuery cq=new CriteriaQuery(TSCategoryEntity.class);
  if (StringUtils.isNotEmpty(comboTree.getId())) {
    cq.createAlias("parent","parent");
    cq.eq("parent.code",comboTree.getId());
  }
 else   if (StringUtils.isNotEmpty(selfCode)) {
    cq.eq("code",selfCode);
  }
 else {
    cq.isNull("parent");
  }
  cq.add();
  List<TSCategoryEntity> categoryList=systemService.getListByCriteriaQuery(cq,false);
  List<ComboTree> comboTrees=new ArrayList<ComboTree>();
  for (int i=0; i < categoryList.size(); i++) {
    comboTrees.add(categoryConvertToTree(categoryList.get(i)));
  }
  return comboTrees;
}
