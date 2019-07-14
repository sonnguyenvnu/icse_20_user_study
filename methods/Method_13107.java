protected TreeNode createTreeNode(Container.Entry entry,String typeName){
  Type type=api.getTypeFactory(entry).make(api,entry,typeName);
  typeName=type.getName();
  List<Container.Entry> entries=getEntries(typeName);
  TreeNode treeNode=new TreeNode(entry,typeName,entries,new TreeNodeBean(type));
  List<String> childTypeNames=getSubTypeNames(typeName);
  if (childTypeNames != null) {
    treeNode.add(new DefaultMutableTreeNode());
  }
  return treeNode;
}
