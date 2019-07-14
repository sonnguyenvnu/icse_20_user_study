private boolean isStringType(ASTName name){
  ASTType type=getTypeNode(name);
  if (type != null) {
    List<ASTClassOrInterfaceType> types=type.findDescendantsOfType(ASTClassOrInterfaceType.class);
    if (!types.isEmpty()) {
      ASTClassOrInterfaceType typeDeclaration=types.get(0);
      if (String.class == typeDeclaration.getType() || "String".equals(typeDeclaration.getImage())) {
        return true;
      }
    }
  }
  return false;
}
