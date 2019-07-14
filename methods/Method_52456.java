@Override public Set<NameDeclaration> findVariableHere(JavaNameOccurrence occurrence){
  if (occurrence.isThisOrSuper() || occurrence.isMethodOrConstructorInvocation()) {
    return Collections.emptySet();
  }
  DeclarationFinderFunction finder=new DeclarationFinderFunction(occurrence);
  Applier.apply(finder,getVariableDeclarations().keySet().iterator());
  if (finder.getDecl() != null) {
    return Collections.singleton(finder.getDecl());
  }
  return Collections.emptySet();
}
