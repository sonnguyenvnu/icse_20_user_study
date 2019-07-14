public Set<String> getExpansionState(){
  Set<String> openedSet=new HashSet<>();
  if (tree != null) {
    int rowCount=tree.getRowCount();
    for (int i=0; i < rowCount; i++) {
      TreePath path=tree.getPathForRow(i);
      if (tree.isExpanded(path)) {
        String rowPathStr=getRowPathStr(path);
        openedSet.addAll(getAllParentPathsStr(rowPathStr));
      }
    }
  }
  return openedSet;
}
