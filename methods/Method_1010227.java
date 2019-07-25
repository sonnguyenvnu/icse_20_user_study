@Override public boolean contains(SNode node){
  if (isExcluded(node)) {
    return false;
  }
  return super.contains(node);
}
