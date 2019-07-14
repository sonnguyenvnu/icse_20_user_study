private Object checkVariableDeclarators(List<String> prefixes,List<String> suffixes,Node root,boolean isStatic,boolean isFinal,Object data){
  for (  ASTVariableDeclarator variableDeclarator : root.findChildrenOfType(ASTVariableDeclarator.class)) {
    for (    ASTVariableDeclaratorId variableDeclaratorId : variableDeclarator.findChildrenOfType(ASTVariableDeclaratorId.class)) {
      checkVariableDeclaratorId(prefixes,suffixes,isStatic,isFinal,variableDeclaratorId,data);
    }
  }
  return data;
}
