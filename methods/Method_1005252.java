@RequestMapping(params="combotree") @ResponseBody public List<ComboTree> combotree(String selfCode,ComboTree comboTree){
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
  ComboTreeModel comboTreeModel=new ComboTreeModel("code","name","list");
  comboTrees=systemService.ComboTree(categoryList,comboTreeModel,null,false);
  MutiLangUtil.setMutiTree(comboTrees);
  return comboTrees;
}
