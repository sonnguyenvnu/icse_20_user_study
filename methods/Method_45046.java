public void updateTree(){
  TreeUtil treeUtil=new TreeUtil(tree);
  treeExpansionState=treeUtil.getExpansionState();
  loadTree();
}
