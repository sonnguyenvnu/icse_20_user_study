@Override public Object visit(ASTFieldDeclarationStatements node,Object data){
  if (node.getModifiers().isPublic() && !node.getModifiers().isStatic()) {
    return NumericConstants.ONE;
  }
  return NumericConstants.ZERO;
}
