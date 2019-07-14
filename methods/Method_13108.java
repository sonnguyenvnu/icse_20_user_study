/** 
 * @param superTreeNode  node to populate
 * @param activeTreeNode active child node
 */
protected void populateTreeNode(TreeNode superTreeNode,TreeNode activeTreeNode){
  superTreeNode.removeAllChildren();
  Container.Entry notNullEntry=superTreeNode.entry;
  if (notNullEntry == null) {
    notNullEntry=activeTreeNode.entry;
  }
  Container preferredContainer=notNullEntry.getContainer();
  String activeTypName=null;
  if (activeTreeNode != null) {
    activeTypName=activeTreeNode.typeName;
  }
  List<String> subTypeNames=getSubTypeNames(superTreeNode.typeName);
  ArrayList<TreeNode> treeNodes=new ArrayList<>();
  for (  String subTypeName : subTypeNames) {
    if (subTypeName.equals(activeTypName)) {
      treeNodes.add(activeTreeNode);
    }
 else {
      List<Container.Entry> entries=getEntries(subTypeName);
      Container.Entry entry=null;
      for (      Container.Entry e : entries) {
        if (e.getContainer() == preferredContainer) {
          entry=e;
        }
      }
      if (entry == null) {
        entry=entries.get(0);
      }
      if (entry != null) {
        Type t=api.getTypeFactory(entry).make(api,entry,subTypeName);
        if (t != null) {
          treeNodes.add(createTreeNode(entry,t.getName()));
        }
      }
    }
  }
  treeNodes.sort(TREE_NODE_COMPARATOR);
  for (  TreeNode treeNode : treeNodes) {
    superTreeNode.add(treeNode);
  }
}
