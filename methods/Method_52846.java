protected NameDeclaration findVariableHere(NameOccurrence occ){
  ImageFinderFunction finder=new ImageFinderFunction(occ.getImage());
  Applier.apply(finder,getDeclarations().keySet().iterator());
  return finder.getDecl();
}
