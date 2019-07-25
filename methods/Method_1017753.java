@Override public Void visit(Node node){
  addLine("Node");
  ++depth;
  node.left().accept(this);
  ++depth;
  node.right().accept(this);
  --depth;
  return null;
}
