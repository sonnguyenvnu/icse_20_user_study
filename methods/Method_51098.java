@Override public int compareOrder(NodeInfo other){
  return Integer.signum(this.id - ((AttributeNode)other).id);
}
