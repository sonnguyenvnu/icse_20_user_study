private boolean isReplaceableArrayLoop(ASTForStatement stmt,List<NameOccurrence> occurrences,VariableNameDeclaration arrayDeclaration){
  String arrayName=arrayDeclaration.getName();
  for (  NameOccurrence occ : occurrences) {
    if (occ.getLocation().getFirstParentOfType(ASTForUpdate.class) == null && occ.getLocation().getFirstParentOfType(ASTExpression.class) != stmt.getFirstChildOfType(ASTExpression.class) && !occurenceIsArrayAccess(occ,arrayName)) {
      return false;
    }
  }
  return true;
}
