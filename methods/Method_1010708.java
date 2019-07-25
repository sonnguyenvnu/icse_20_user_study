@Override public void execute(@NotNull String pattern){
  SNode newChild=getSubstituteItem().createNode(pattern);
  final SNode nodeToSelect=doSubstitute(newChild);
  if (nodeToSelect != null) {
    myContext.getEditorContext().flushEvents();
    select(nodeToSelect,pattern);
  }
}
