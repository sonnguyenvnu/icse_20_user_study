@Override public int jjtGetNumChildren(){
  return node.hasChildNodes() ? node.getChildNodes().getLength() : 0;
}
