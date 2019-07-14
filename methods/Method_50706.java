@Override public Object visit(ASTVariableDeclaration node,Object data){
  if (node.getFirstParentOfType(ASTVariableDeclarationStatements.class).getModifiers().isFinal()) {
    checkMatches(FINAL_REGEX,node,data);
  }
 else {
    checkMatches(LOCAL_REGEX,node,data);
  }
  return data;
}
