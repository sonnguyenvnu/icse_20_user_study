public List<Node<T>> parents(){
  LinkedList<Node<T>> list=new LinkedList<Node<T>>();
  Node<T> me=parent;
  while (me != null) {
    list.addFirst(me);
    me=me.parent();
  }
  return list;
}
