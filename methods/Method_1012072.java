private void register(SNodeGroupTreeNode parent,SNodeGroupTreeNode groupTreeNode){
  final String rp=groupTreeNode.getText();
  if (parent == null) {
    int index=-1;
    for (int i=0; i < myRootGroups.size(); i++) {
      SNodeGroupTreeNode group=myRootGroups.get(i);
      if (rp.compareTo(group.getText()) < 0) {
        index=i;
        break;
      }
    }
    if (index == -1) {
      index=myRootGroups.size();
    }
    myRootGroups.add(index,groupTreeNode);
    if (myInitialized || myInitializing) {
      DefaultTreeModel treeModel=getTree().getModel();
      treeModel.insertNodeInto(groupTreeNode,this,index + myChildModelTreeNodes.size());
    }
  }
 else {
    int index=-1;
    int groupCount=0;
    for (int i=0; i < parent.getChildCount(); i++) {
      if (!(parent.getChildAt(i) instanceof SNodeGroupTreeNode)) {
        break;
      }
      groupCount++;
      SNodeGroupTreeNode group=(SNodeGroupTreeNode)parent.getChildAt(i);
      if (rp.compareTo(group.getText()) < 0) {
        index=i;
        break;
      }
    }
    if (index == -1) {
      index=groupCount;
    }
    if (myInitialized || myInitializing) {
      DefaultTreeModel treeModel=getTree().getModel();
      treeModel.insertNodeInto(groupTreeNode,parent,index);
    }
 else {
      parent.insert(groupTreeNode,index);
    }
  }
}
