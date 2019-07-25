@Override public boolean merge(MergeableUsage mergeableUsage){
  return mergeableUsage instanceof NodeUsage && myNode.equals(((NodeUsage)mergeableUsage).myNode);
}
