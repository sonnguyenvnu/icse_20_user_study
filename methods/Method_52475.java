@Override protected Set<NameDeclaration> findVariableHere(JavaNameOccurrence occ){
  ImageFinderFunction finder=new ImageFinderFunction(occ.getImage());
  Applier.apply(finder,getDeclarations().keySet().iterator());
  if (finder.getDecl() != null) {
    return Collections.singleton(finder.getDecl());
  }
  return Collections.emptySet();
}
