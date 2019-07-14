private void refreshGroups(List<TreeTableColumn<S,?>> groupColumns){
  groups=new HashMap<>();
  for (  TreeTableColumn<S,?> treeTableColumn : groupColumns) {
    groups=group(treeTableColumn,groups,null,(RecursiveTreeItem<S>)originalRoot);
  }
  groupOrder.addAll(groupColumns);
  buildGroupedRoot(groups,null,0);
}
