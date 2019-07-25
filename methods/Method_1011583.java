@Override protected SNodeReference wrap(SNode node){
  return new SNodePointer(node);
}
