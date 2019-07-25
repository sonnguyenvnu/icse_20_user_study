@Override public boolean needed(MPSTreeNode node){
  return !(EqualUtil.equals(node.getErrorState(),myErrorState) && EqualUtil.equals(node.getTooltipText(),myTooltipText));
}
