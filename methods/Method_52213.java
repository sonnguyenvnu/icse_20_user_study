@Override public Object visit(ASTLocalVariableDeclaration node,Object data){
  if (!checkLocals) {
    return data;
  }
  return checkVariableDeclarators(localPrefixes,localSuffixes,node,false,node.isFinal(),data);
}
