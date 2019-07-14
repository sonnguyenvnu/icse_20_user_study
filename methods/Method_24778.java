static protected String getNodeAsString(ASTNode node){
  if (node == null)   return "NULL";
  String className=node.getClass().getName();
  int index=className.lastIndexOf(".");
  if (index > 0)   className=className.substring(index + 1);
  String value=className;
  if (node instanceof TypeDeclaration)   value=((TypeDeclaration)node).getName().toString() + " | " + className;
 else   if (node instanceof MethodDeclaration)   value=((MethodDeclaration)node).getName().toString() + " | " + className;
 else   if (node instanceof MethodInvocation)   value=((MethodInvocation)node).getName().toString() + " | " + className;
 else   if (node instanceof FieldDeclaration)   value=node.toString() + " FldDecl | ";
 else   if (node instanceof SingleVariableDeclaration)   value=((SingleVariableDeclaration)node).getName() + " - " + ((SingleVariableDeclaration)node).getType() + " | SVD ";
 else   if (node instanceof ExpressionStatement)   value=node.toString() + className;
 else   if (node instanceof SimpleName)   value=((SimpleName)node).getFullyQualifiedName() + " | " + className;
 else   if (node instanceof QualifiedName)   value=node.toString() + " | " + className;
 else   if (node instanceof FieldAccess)   value=node.toString() + " | ";
 else   if (className.startsWith("Variable"))   value=node.toString() + " | " + className;
 else   if (className.endsWith("Type"))   value=node.toString() + " | " + className;
  value+=" [" + node.getStartPosition() + "," + (node.getStartPosition() + node.getLength()) + "]";
  value+=" Line: " + ((CompilationUnit)node.getRoot()).getLineNumber(node.getStartPosition());
  return value;
}
