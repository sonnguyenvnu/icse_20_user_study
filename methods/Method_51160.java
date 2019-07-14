/** 
 * Checks if the packageName or the className is a child of the current (this.level) node. If it's true, the current node changes to the child node.
 */
private boolean isStringInLevel(String str){
  for (int i=0; i < level.getChildCount(); i++) {
    final AbstractReportNode child=level.getChildAt(i);
    final String tmp;
    if (child instanceof PackageNode) {
      tmp=((PackageNode)child).getPackageName();
    }
 else     if (child instanceof ClassNode) {
      tmp=((ClassNode)child).getClassName();
    }
 else {
      return false;
    }
    if (tmp != null && tmp.equals(str)) {
      level=child;
      return true;
    }
  }
  return false;
}
