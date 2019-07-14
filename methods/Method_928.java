public static boolean isParentOrSelf(Node descendant,Node ancestor){
  if (descendant == ancestor) {
    return true;
  }
  if (descendant == null || ancestor == null) {
    return false;
  }
  Node parent=descendant.jjtGetParent();
  while (parent != ancestor && parent != null) {
    parent=parent.jjtGetParent();
  }
  return parent == ancestor;
}
