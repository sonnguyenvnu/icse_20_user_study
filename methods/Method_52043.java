private boolean isLiteral(Node equality,int child){
  Node target=equality.jjtGetChild(child);
  target=getFirstChildOrThis(target);
  target=getFirstChildOrThis(target);
  return target instanceof ASTLiteral;
}
