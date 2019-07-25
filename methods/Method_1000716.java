public Node<T> desc(int... indexes){
  Node<T> me=this;
  for (  int i : indexes) {
    if (!me.hasChild())     return null;
    me=me.firstChild().next(i);
  }
  return me;
}
