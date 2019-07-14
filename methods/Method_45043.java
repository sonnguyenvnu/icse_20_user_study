public DefaultMutableTreeNode loadNodesByUserObj(DefaultMutableTreeNode node,List<TreeNodeUserObject> args){
  if (args.size() > 0) {
    TreeNodeUserObject name=args.remove(0);
    DefaultMutableTreeNode nod=getChild(node,name);
    if (nod == null)     nod=new DefaultMutableTreeNode(name);
    node.add(loadNodesByUserObj(nod,args));
  }
  return node;
}
