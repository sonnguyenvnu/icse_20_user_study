@SuppressWarnings("unchecked") public Node<T> add(Node<?>... nodes){
  if (nodes.length == 0) {
    return this;
  }
  if (nodes.length == 1) {
    SimpleNode<T> node=(SimpleNode<T>)nodes[0];
    node.parent=this;
    if (!this.hasChild()) {
      firstChild=node;
      lastChild=node;
      node.next=null;
      node.prev=null;
    }
 else {
      lastChild.next=node;
      node.prev=lastChild;
      node.next=null;
      lastChild=node;
    }
  }
 else {
    SimpleNode<T> theNode=(SimpleNode<T>)nodes[0];
    theNode.parent=this;
    theNode.next=(SimpleNode<T>)nodes[1];
    if (null == lastChild) {
      firstChild=theNode;
    }
 else {
      lastChild.next=theNode;
    }
    int i=1;
    for (; i < nodes.length - 1; i++) {
      SimpleNode<T> node=(SimpleNode<T>)nodes[i];
      node.parent=this;
      node.prev=(SimpleNode<T>)nodes[i - 1];
      node.next=(SimpleNode<T>)nodes[i + 1];
    }
    lastChild=(SimpleNode<T>)nodes[i];
    lastChild.parent=this;
    lastChild.prev=(SimpleNode<T>)nodes[i - 1];
  }
  return this;
}
