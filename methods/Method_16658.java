protected List<String> getTreePathByTerm(List<Object> termValue){
  List<PK> idList=((List)termValue);
  return treeService.selectByPk(idList).stream().map(TreeSupportEntity::getPath).filter(Objects::nonNull).collect(Collectors.toList());
}
