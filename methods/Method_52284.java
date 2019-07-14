private boolean isSerialVersionUID(ASTFieldDeclaration field){
  return "serialVersionUID".equals(field.getVariableName()) && field.isStatic() && field.isFinal() && field.getType() == long.class;
}
