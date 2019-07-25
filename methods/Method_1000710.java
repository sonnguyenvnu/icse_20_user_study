public Node<T> prev(Node<T> node){
  SimpleNode<T> nd=(SimpleNode<T>)node;
  this.prev=nd;
  nd.next=this;
  nd.parent=parent;
  return this;
}
