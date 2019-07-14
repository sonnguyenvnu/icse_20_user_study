@Override public boolean equalsNode(AbstractReportNode arg0){
  if (!(arg0 instanceof PackageNode)) {
    return false;
  }
  return ((PackageNode)arg0).getPackageName().equals(this.packageName);
}
