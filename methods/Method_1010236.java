@Override public SNode set(int index,SNode node){
  return SNodeUtil.replaceWithAnother(get(index),node);
}
