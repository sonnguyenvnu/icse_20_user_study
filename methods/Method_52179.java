@Override public Object visit(ASTVariableDeclaratorId node,Object data){
  if (node.isExceptionBlockParameter()) {
    checkMatches(node,exceptionBlockParameterRegex,data);
  }
 else   if (node.isLocalVariable()) {
    checkMatches(node,node.isFinal() ? finalVarRegex : localVarRegex,data);
  }
  return data;
}
