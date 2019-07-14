private void checkAll(Object context,ASTFormalParameter[] arrs,List<ASTBlockStatement> bs){
  for (  ASTFormalParameter element : arrs) {
    checkForDirectAssignment(context,element,bs);
  }
}
