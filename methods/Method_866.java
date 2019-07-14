@Override protected SortedMap<Integer,Node> orderedCommentsAndDeclarations(ASTCompilationUnit cUnit){
  SortedMap<Integer,Node> itemsByLineNumber=new TreeMap<>();
  List<ASTImportDeclaration> importDecl=cUnit.findDescendantsOfType(ASTImportDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,importDecl);
  List<ASTClassOrInterfaceDeclaration> classDecl=cUnit.findDescendantsOfType(ASTClassOrInterfaceDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,classDecl);
  List<ASTFieldDeclaration> fields=cUnit.findDescendantsOfType(ASTFieldDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,fields);
  List<ASTMethodDeclaration> methods=cUnit.findDescendantsOfType(ASTMethodDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,methods);
  List<ASTConstructorDeclaration> constructors=cUnit.findDescendantsOfType(ASTConstructorDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,constructors);
  List<ASTBlockStatement> blockStatements=cUnit.findDescendantsOfType(ASTBlockStatement.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,blockStatements);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,cUnit.getComments());
  return itemsByLineNumber;
}
