@Override protected SortedMap<Integer,Node> orderedCommentsAndDeclarations(ASTCompilationUnit cUnit){
  SortedMap<Integer,Node> itemsByLineNumber=new TreeMap<>();
  List<ASTClassOrInterfaceDeclaration> packageDecl=cUnit.findDescendantsOfType(ASTClassOrInterfaceDeclaration.class);
  addDeclarations(itemsByLineNumber,packageDecl);
  List<ASTEnumDeclaration> enumDecl=cUnit.findDescendantsOfType(ASTEnumDeclaration.class);
  addDeclarations(itemsByLineNumber,enumDecl);
  List<ASTAnnotationTypeDeclaration> annotationDecl=cUnit.findDescendantsOfType(ASTAnnotationTypeDeclaration.class);
  addDeclarations(itemsByLineNumber,annotationDecl);
  addDeclarations(itemsByLineNumber,cUnit.getComments());
  return itemsByLineNumber;
}
