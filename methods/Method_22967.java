protected void updateExpanded(JTree tree){
  Enumeration en=tree.getExpandedDescendants(new TreePath(tree.getModel().getRoot()));
  StringBuilder s=new StringBuilder();
  while (en.hasMoreElements()) {
    TreePath tp=(TreePath)en.nextElement();
    Object[] path=tp.getPath();
    for (    Object o : path) {
      DefaultMutableTreeNode p=(DefaultMutableTreeNode)o;
      String name=(String)p.getUserObject();
      s.append(name);
      s.append(File.separatorChar);
    }
    s.setCharAt(s.length() - 1,File.pathSeparatorChar);
  }
  s.setLength(s.length() - 1);
  String pref="examples." + getClass().getName() + ".visible";
  Preferences.set(pref,s.toString());
  Preferences.save();
}
