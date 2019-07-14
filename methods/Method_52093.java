private boolean indexStartsAtZero(VariableNameDeclaration index){
  ASTVariableDeclaratorId name=(ASTVariableDeclaratorId)index.getNode();
  ASTVariableDeclarator declarator=name.getFirstParentOfType(ASTVariableDeclarator.class);
  if (declarator == null) {
    return false;
  }
  try {
    List<Node> zeroLiteral=declarator.findChildNodesWithXPath("./VariableInitializer/Expression/PrimaryExpression/PrimaryPrefix/Literal[@Image='0' and " + "@StringLiteral='false']");
    if (!zeroLiteral.isEmpty()) {
      return true;
    }
  }
 catch (  JaxenException je) {
    throw new RuntimeException(je);
  }
  return false;
}
