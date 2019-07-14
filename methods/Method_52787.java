public List<NameOccurrence> getUsages(){
  return getScope().getDeclarations().get(nameDeclaration);
}
