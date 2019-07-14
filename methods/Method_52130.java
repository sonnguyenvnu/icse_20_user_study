private boolean calledFromOutsideItself(List<NameOccurrence> occs,NameDeclaration mnd){
  int callsFromOutsideMethod=0;
  for (  NameOccurrence occ : occs) {
    Node occNode=occ.getLocation();
    ASTConstructorDeclaration enclosingConstructor=occNode.getFirstParentOfType(ASTConstructorDeclaration.class);
    if (enclosingConstructor != null) {
      callsFromOutsideMethod++;
      break;
    }
    ASTInitializer enclosingInitializer=occNode.getFirstParentOfType(ASTInitializer.class);
    if (enclosingInitializer != null) {
      callsFromOutsideMethod++;
      break;
    }
    ASTMethodDeclaration enclosingMethod=occNode.getFirstParentOfType(ASTMethodDeclaration.class);
    if (enclosingMethod == null || !mnd.getNode().jjtGetParent().equals(enclosingMethod)) {
      callsFromOutsideMethod++;
    }
  }
  return callsFromOutsideMethod == 0;
}
