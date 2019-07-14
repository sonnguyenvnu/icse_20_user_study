@Override public boolean equalsNode(AbstractReportNode arg0){
  if (!(arg0 instanceof ClassNode)) {
    return false;
  }
  return ((ClassNode)arg0).getClassName().equals(className);
}
