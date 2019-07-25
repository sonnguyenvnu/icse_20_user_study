@Override public boolean reorder(@NotNull MPSTreeNode parent,@NotNull List<MPSTreeNode> childrenToSort){
  if (!(parent instanceof SModelTreeNode)) {
    return false;
  }
  Comparator<MPSTreeNode> byName=Comparator.comparing(MPSTreeNode::getText,String.CASE_INSENSITIVE_ORDER);
  childrenToSort.sort(new GroupByFirstClassEncounter<>(childrenToSort).thenComparing(byName));
  if (myGroupByConcept != null && myGroupByConcept.test(parent)) {
    int start=-1, stop=childrenToSort.size();
    for (int i=0, x=childrenToSort.size(); i < x; i++) {
      if (childrenToSort.get(i) instanceof SNodeTreeNode) {
        if (start == -1) {
          start=i;
        }
      }
 else {
        if (start != -1) {
          stop=i;
          break;
        }
      }
    }
    if (start != -1) {
      assert start < stop;
      Comparator<MPSTreeNode> c2=(o1,o2) -> {
        SNode tn1=((SNodeTreeNode)o1).getSNode();
        SNode tn2=((SNodeTreeNode)o2).getSNode();
        if (tn1 == tn2) {
          return 0;
        }
        if (tn1 == null) {
          return -1;
        }
        if (tn2 == null) {
          return 1;
        }
        return tn1.getConcept().getName().compareTo(tn2.getConcept().getName());
      }
;
      childrenToSort.subList(start,stop).sort(c2.thenComparing(byName));
    }
  }
  return true;
}
