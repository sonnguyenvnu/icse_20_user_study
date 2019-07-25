public Node<T> next(Node<T> node){
  SimpleNode<T> nd=(SimpleNode<T>)node;
  this.next=nd;
  nd.prev=this;
  nd.parent=this;
  return this;
}
