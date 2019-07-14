private void findFieldLiterals(final ASTField fDecl){
  if ("String".equals(fDecl.getType()) && AUTHORIZATION.equalsIgnoreCase(fDecl.getValue())) {
    listOfAuthorizationVariables.add(Helper.getFQVariableName(fDecl));
  }
}
