public Set<NameDeclaration> findVariableHere(PLSQLNameOccurrence occurrence){
  Set<NameDeclaration> result=new HashSet<>();
  if (occurrence.isThisOrSuper() || occurrence.isMethodOrConstructorInvocation()) {
    return result;
  }
  ImageFinderFunction finder=new ImageFinderFunction(occurrence.getImage());
  Applier.apply(finder,getVariableDeclarations().keySet().iterator());
  if (finder.getDecl() != null) {
    result.add(finder.getDecl());
  }
  return result;
}
