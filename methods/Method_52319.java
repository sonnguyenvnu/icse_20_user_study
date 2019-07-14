private ASTExpression getAllocationFirstArgument(ASTExpression expression){
  List<ASTAllocationExpression> allocations=expression.findDescendantsOfType(ASTAllocationExpression.class);
  ASTExpression firstArgument=null;
  if (!allocations.isEmpty()) {
    ASTArgumentList argumentList=allocations.get(allocations.size() - 1).getFirstDescendantOfType(ASTArgumentList.class);
    if (argumentList != null) {
      firstArgument=argumentList.getFirstChildOfType(ASTExpression.class);
    }
  }
  if (firstArgument != null && firstArgument.getFirstDescendantOfType(ASTName.class) != null) {
    ASTName name=firstArgument.getFirstDescendantOfType(ASTName.class);
    Map<VariableNameDeclaration,List<NameOccurrence>> vars=firstArgument.getScope().getDeclarations(VariableNameDeclaration.class);
    for (    VariableNameDeclaration nameDecl : vars.keySet()) {
      if (nameDecl.getName().equals(name.getImage()) && isResourceTypeOrSubtype(firstArgument)) {
        return firstArgument;
      }
    }
  }
  return null;
}
