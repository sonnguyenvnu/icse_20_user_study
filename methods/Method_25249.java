private static boolean isCatchVariable(VariableDeclarationNode node){
  return elementFromDeclaration(node.getTree()).getKind() == EXCEPTION_PARAMETER;
}
