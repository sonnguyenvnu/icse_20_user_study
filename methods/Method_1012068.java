private void populate(){
  final LongModelNameText fullModelName=new LongModelNameText();
  GroupByForkBranchNamespaceBuilder treeBuilder=new GroupByForkBranchNamespaceBuilder();
  ArrayList<SModel> sortedModels=new ArrayList<>(IterableUtil.asCollection(getModule().getModels()));
  Collections.sort(sortedModels,new SModelComparator());
  sortedModels.stream().map(m -> new SModelTreeNode(m,fullModelName)).forEach(treeBuilder::addNode);
  treeBuilder.fillNode(this);
}
