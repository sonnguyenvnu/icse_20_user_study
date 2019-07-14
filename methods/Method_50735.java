private void innerAddParametrizedClassToMapping(final ASTFieldDeclaration node,final ClassTypeRef innerClassRef){
  List<Identifier> ids=innerClassRef.getNames();
  StringBuffer argType=new StringBuffer();
  for (  Identifier id : ids) {
    argType.append(id.getValue()).append(".");
  }
  argType.deleteCharAt(argType.length() - 1);
  addVariableToMapping(Helper.getFQVariableName(node),argType.toString());
}
