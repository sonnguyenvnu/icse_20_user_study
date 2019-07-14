protected SortedMap<Integer,Node> orderedComments(ASTCompilationUnit cUnit){
  SortedMap<Integer,Node> itemsByLineNumber=new TreeMap<>();
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,cUnit.getComments());
  List<ASTAnnotation> annotations=cUnit.findDescendantsOfType(ASTAnnotation.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,annotations);
  List<ASTClassOrInterfaceDeclaration> classDecl=cUnit.findDescendantsOfType(ASTClassOrInterfaceDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,classDecl);
  List<ASTFieldDeclaration> fields=cUnit.findDescendantsOfType(ASTFieldDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,fields);
  List<ASTMethodDeclaration> methods=cUnit.findDescendantsOfType(ASTMethodDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,methods);
  List<ASTConstructorDeclaration> constructors=cUnit.findDescendantsOfType(ASTConstructorDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,constructors);
  List<ASTEnumDeclaration> enumDecl=cUnit.findDescendantsOfType(ASTEnumDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,enumDecl);
  return itemsByLineNumber;
}
