public Node<T> top(){
  if (null == parent)   return this;
  return parent.top();
}
