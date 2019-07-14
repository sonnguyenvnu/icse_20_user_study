private Node skipAnnotations(Node p){
  int index=0;
  Node n=p.jjtGetChild(index++);
  while (n instanceof ASTAnnotation && index < p.jjtGetNumChildren()) {
    n=p.jjtGetChild(index++);
  }
  return n;
}
