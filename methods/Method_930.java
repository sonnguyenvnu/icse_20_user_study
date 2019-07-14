public static boolean isConstant(ASTFieldDeclaration field){
  return field != null && field.isStatic() && field.isFinal();
}
