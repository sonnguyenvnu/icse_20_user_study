public List<NameOccurrence> getUsages(){
  return getScope().getDeclarations(VariableNameDeclaration.class).get(nameDeclaration);
}
