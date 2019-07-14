private boolean isResourceTypeOrSubtype(TypeNode refType){
  if (refType.getType() != null) {
    for (    String type : types) {
      if (TypeHelper.isA(refType,type)) {
        return true;
      }
    }
  }
 else   if (refType.jjtGetChild(0) instanceof ASTReferenceType) {
    ASTReferenceType ref=(ASTReferenceType)refType.jjtGetChild(0);
    if (ref.jjtGetChild(0) instanceof ASTClassOrInterfaceType) {
      ASTClassOrInterfaceType clazz=(ASTClassOrInterfaceType)ref.jjtGetChild(0);
      if (simpleTypes.contains(toSimpleType(clazz.getImage())) && !clazz.isReferenceToClassSameCompilationUnit() || types.contains(clazz.getImage()) && !clazz.isReferenceToClassSameCompilationUnit()) {
        return true;
      }
    }
  }
  return false;
}
