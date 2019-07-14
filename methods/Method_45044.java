@SuppressWarnings("unchecked") public DefaultMutableTreeNode getChild(DefaultMutableTreeNode node,TreeNodeUserObject name){
  Enumeration<DefaultMutableTreeNode> entry=node.children();
  while (entry.hasMoreElements()) {
    DefaultMutableTreeNode nods=entry.nextElement();
    if (((TreeNodeUserObject)nods.getUserObject()).getOriginalName().equals(name.getOriginalName())) {
      return nods;
    }
  }
  return null;
}
