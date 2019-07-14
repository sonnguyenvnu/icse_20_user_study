void expandTree(JTree tree,Object object,String[] items,DefaultMutableTreeNode[] nodes,int index){
  TreeModel model=tree.getModel();
  if (index == 0) {
    nodes[0]=(DefaultMutableTreeNode)model.getRoot();
    expandTree(tree,nodes[0],items,nodes,1);
  }
 else   if (index < items.length) {
    DefaultMutableTreeNode node=(DefaultMutableTreeNode)object;
    int count=model.getChildCount(node);
    for (int i=0; i < count; i++) {
      DefaultMutableTreeNode child=(DefaultMutableTreeNode)model.getChild(node,i);
      if (items[index].equals(child.getUserObject())) {
        nodes[index]=child;
        expandTree(tree,child,items,nodes,index + 1);
      }
    }
  }
 else {
    tree.expandPath(new TreePath(nodes));
  }
}
