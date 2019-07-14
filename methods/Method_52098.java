private boolean isReplaceableListLoop(ASTForStatement stmt,List<NameOccurrence> occurrences,VariableNameDeclaration listDeclaration){
  String listName=listDeclaration.getName();
  for (  NameOccurrence occ : occurrences) {
    if (occ.getLocation().getFirstParentOfType(ASTForUpdate.class) == null && occ.getLocation().getFirstParentOfType(ASTExpression.class) != stmt.getFirstChildOfType(ASTExpression.class) && !occurenceIsListGet(occ,listName)) {
      return false;
    }
  }
  return true;
}
