@Override public boolean needed(MPSTreeNode node){
  return !EqualUtil.equals(node.getAdditionalText(),myText);
}
