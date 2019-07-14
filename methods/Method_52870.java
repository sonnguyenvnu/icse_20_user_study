@Override public Object visit(ASTHtmlScript node,Object data){
  checkIfCorrectlyEscaped(node,data);
  return super.visit(node,data);
}
