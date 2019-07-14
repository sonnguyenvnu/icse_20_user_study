public ClassNameDeclaration findClassNameDeclaration(String name){
  ImageFinderFunction finder=new ImageFinderFunction(name);
  Applier.apply(finder,getClassDeclarations().keySet().iterator());
  return (ClassNameDeclaration)finder.getDecl();
}
