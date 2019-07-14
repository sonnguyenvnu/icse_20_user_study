/** 
 * performs a check on the variable and updates the counter. Counter is instance for a class and is reset upon new class scan.
 * @param variableType The variable type.
 */
private void checkVariableType(Node nameNode,String variableType){
  if (nameNode.getParentsOfType(ASTClassOrInterfaceDeclaration.class).isEmpty()) {
    return;
  }
  ClassScope clzScope=((JavaNode)nameNode).getScope().getEnclosingScope(ClassScope.class);
  if (!clzScope.getClassName().equals(variableType) && !this.filterTypes(variableType) && !this.typesFoundSoFar.contains(variableType)) {
    couplingCount++;
    typesFoundSoFar.add(variableType);
  }
}
