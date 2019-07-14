/** 
 * Allow mocking APIs that return obsolete types. 
 */
private boolean mockingObsoleteMethod(MethodTree enclosingMethod,VisitorState state,Type type){
  boolean[] found={false};
  enclosingMethod.accept(new TreeScanner<Void,Void>(){
    @Override public Void visitMethodInvocation(    MethodInvocationTree node,    Void unused){
      if (found[0]) {
        return null;
      }
      if (MOCKITO_MATCHER.matches(node,state)) {
        Type stubber=ASTHelpers.getReturnType(node);
        if (!stubber.getTypeArguments().isEmpty() && ASTHelpers.isSameType(getOnlyElement(stubber.getTypeArguments()),type,state)) {
          found[0]=true;
        }
      }
      return super.visitMethodInvocation(node,null);
    }
  }
,null);
  return found[0];
}
