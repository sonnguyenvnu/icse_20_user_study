boolean reorder(@NotNull AbstractProjectViewPane viewPane,@NotNull MPSTreeNode parent,@NotNull List<MPSTreeNode> childrenToSort){
  ChildComparatorProvider neo=null;
  int neoWeight=Integer.MIN_VALUE;
  for (  ChildComparatorProvider p : myProviders) {
    if (!p.isApplicable(viewPane.getId(),viewPane.getSubId())) {
      continue;
    }
    final int w=p.weightFor(parent);
    if (neoWeight < w) {
      neo=p;
      neoWeight=w;
    }
  }
  if (neo == null) {
    return false;
  }
  final Comparator<? super MPSTreeNode> comparator=neo.comparator(parent);
  Objects.requireNonNull(comparator,String.format("Provider %s claimed priority %d for tree node %s but didn't supply any comparator",neo,neoWeight,parent.getClass()));
  childrenToSort.sort(comparator);
  return true;
}
