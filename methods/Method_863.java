private SortedMap<Integer,Node> orderedCommentsAndEnumDeclarations(ASTCompilationUnit cUnit){
  SortedMap<Integer,Node> itemsByLineNumber=new TreeMap<>();
  List<ASTEnumDeclaration> enumDecl=cUnit.findDescendantsOfType(ASTEnumDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,enumDecl);
  List<ASTEnumConstant> contantDecl=cUnit.findDescendantsOfType(ASTEnumConstant.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,contantDecl);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,cUnit.getComments());
  return itemsByLineNumber;
}
