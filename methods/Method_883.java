private boolean checkThreadLocalWithInitalValue(ASTFieldDeclaration fieldDeclaration){
  ASTVariableDeclarator variableDeclarator=fieldDeclaration.getFirstDescendantOfType(ASTVariableDeclarator.class);
  if (variableDeclarator == null) {
    return false;
  }
  List<ASTMethodDeclarator> astMethodDeclaratorList=variableDeclarator.findDescendantsOfType(ASTMethodDeclarator.class,true);
  if (!astMethodDeclaratorList.isEmpty()) {
    return METHOD_INITIAL_VALUE.equals(astMethodDeclaratorList.get(0).getImage());
  }
  ASTName name=variableDeclarator.getFirstDescendantOfType(ASTName.class);
  return name != null && WITH_INITIAL.equals(name.getImage());
}
