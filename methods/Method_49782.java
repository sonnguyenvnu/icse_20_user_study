public NodeList getElementsByTagName(String name){
  return new NodeListImpl(this,name,true);
}
