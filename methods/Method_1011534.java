@Override protected MPSTreeNode rebuild(){
  return new GroupedTree.GroupTreeNode(createRootGroupKind(),new Object(),getData());
}
