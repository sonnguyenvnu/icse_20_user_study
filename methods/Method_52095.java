private Entry<VariableNameDeclaration,List<NameOccurrence>> getIterableDeclOfIteratorLoop(VariableNameDeclaration indexDecl,Scope scope){
  Node initializer=indexDecl.getNode().getFirstParentOfType(ASTVariableDeclarator.class).getFirstChildOfType(ASTVariableInitializer.class);
  if (initializer == null) {
    return null;
  }
  ASTName nameNode=initializer.getFirstDescendantOfType(ASTName.class);
  if (nameNode == null) {
    return null;
  }
  String name=nameNode.getImage();
  int dotIndex=name.indexOf('.');
  if (dotIndex > 0) {
    name=name.substring(0,dotIndex);
  }
  return findDeclaration(name,scope);
}
