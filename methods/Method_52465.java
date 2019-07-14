public void execute(Scope startingScope){
  Set<NameDeclaration> found=searchUpward(occ,startingScope);
  if (TRACE) {
    System.out.println("found " + found);
  }
  declarations.addAll(found);
}
