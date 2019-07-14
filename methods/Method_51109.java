@Override public int compareOrder(NodeInfo other){
  return Integer.signum(this.node.jjtGetId() - ((ElementNode)other).node.jjtGetId());
}
