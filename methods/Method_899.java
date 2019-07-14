@Override public Object visit(final ASTVariableDeclaratorId node,Object data){
  if (variableNamingStartOrEndWithDollarAndUnderLine(node.getImage())) {
    return super.visit(node,data);
  }
  ASTTypeDeclaration typeDeclaration=node.getFirstParentOfType(ASTTypeDeclaration.class);
  Node jjtGetChild=typeDeclaration.jjtGetChild(0);
  if (jjtGetChild instanceof ASTAnnotationTypeDeclaration) {
    return super.visit(node,data);
  }
  ASTFieldDeclaration astFieldDeclaration=node.getFirstParentOfType(ASTFieldDeclaration.class);
  boolean isNotCheck=astFieldDeclaration != null && (astFieldDeclaration.isFinal() || astFieldDeclaration.isStatic());
  if (isNotCheck) {
    return super.visit(node,data);
  }
  if (!(pattern.matcher(node.getImage()).matches())) {
    ViolationUtils.addViolationWithPrecisePosition(this,node,data,I18nResources.getMessage(MESSAGE_KEY_PREFIX + ".variable",node.getImage()));
  }
  return super.visit(node,data);
}
