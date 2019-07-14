protected SortedMap<Integer,Node> orderedCommentsAndDeclarations(ASTCompilationUnit cUnit){
  SortedMap<Integer,Node> itemsByLineNumber=new TreeMap<>();
  addDeclarations(itemsByLineNumber,cUnit.findDescendantsOfType(ASTClassOrInterfaceDeclaration.class,true));
  addDeclarations(itemsByLineNumber,cUnit.getComments());
  addDeclarations(itemsByLineNumber,cUnit.findDescendantsOfType(ASTFieldDeclaration.class,true));
  addDeclarations(itemsByLineNumber,cUnit.findDescendantsOfType(ASTMethodDeclaration.class,true));
  addDeclarations(itemsByLineNumber,cUnit.findDescendantsOfType(ASTConstructorDeclaration.class,true));
  addDeclarations(itemsByLineNumber,cUnit.findDescendantsOfType(ASTEnumDeclaration.class,true));
  return itemsByLineNumber;
}
