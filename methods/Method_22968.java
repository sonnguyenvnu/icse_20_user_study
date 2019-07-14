protected void restoreExpanded(JTree tree){
  String pref="examples." + getClass().getName() + ".visible";
  String value=Preferences.get(pref);
  if (value != null) {
    String[] paths=PApplet.split(value,File.pathSeparator);
    for (    String path : paths) {
      String[] items=PApplet.split(path,File.separator);
      DefaultMutableTreeNode[] nodes=new DefaultMutableTreeNode[items.length];
      expandTree(tree,null,items,nodes,0);
    }
  }
}
