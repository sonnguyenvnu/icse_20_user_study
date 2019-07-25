public int depth(){
  int re=0;
  Node<T> nd=this;
  while (null != nd.parent()) {
    re++;
    nd=nd.parent();
  }
  return re;
}
