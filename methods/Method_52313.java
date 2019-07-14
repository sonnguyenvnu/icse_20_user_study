@Override public Object visit(final ASTMethodDeclaration node,final Object data){
  final ASTMethodDeclarator methodDeclarator=node.getFirstChildOfType(ASTMethodDeclarator.class);
  if (!isCloneMethod(methodDeclarator)) {
    return data;
  }
  final ASTClassOrInterfaceDeclaration classOrInterface=node.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);
  if (classOrInterface != null && (node.isFinal() || classOrInterface.isFinal())) {
    if (node.findDescendantsOfType(ASTBlock.class).size() == 1) {
      final List<ASTBlockStatement> blocks=node.findDescendantsOfType(ASTBlockStatement.class);
      if (blocks.size() == 1) {
        final ASTBlockStatement block=blocks.get(0);
        final ASTClassOrInterfaceType type=block.getFirstDescendantOfType(ASTClassOrInterfaceType.class);
        if (type != null && type.getType() != null && type.getNthParent(9).equals(node) && type.getType().equals(CloneNotSupportedException.class)) {
          return data;
        }
 else         if (type != null && type.getType() == null && "CloneNotSupportedException".equals(type.getImage())) {
          return data;
        }
      }
    }
  }
  if (classOrInterface != null && classOrInterface.getType() == null) {
    final Set<String> classesNames=determineTopLevelCloneableClasses(classOrInterface);
    final ASTImplementsList implementsList=classOrInterface.getFirstChildOfType(ASTImplementsList.class);
    if (implementsList != null) {
      final List<ASTClassOrInterfaceType> types=implementsList.findChildrenOfType(ASTClassOrInterfaceType.class);
      for (      final ASTClassOrInterfaceType t : types) {
        if (classesNames.contains(t.getImage())) {
          return data;
        }
      }
    }
    final ASTExtendsList extendsList=classOrInterface.getFirstChildOfType(ASTExtendsList.class);
    if (extendsList != null) {
      final ASTClassOrInterfaceType type=extendsList.getFirstChildOfType(ASTClassOrInterfaceType.class);
      if (classesNames.contains(type.getImage())) {
        return data;
      }
    }
  }
  addViolation(data,node);
  return data;
}
