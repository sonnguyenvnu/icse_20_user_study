/** 
 * Gives the return type of an ExpressionTree that represents a method select. <p>TODO(eaftan): Are there other places this could be used?
 */
public static Type getReturnType(ExpressionTree expressionTree){
  if (expressionTree instanceof JCFieldAccess) {
    JCFieldAccess methodCall=(JCFieldAccess)expressionTree;
    return methodCall.type.getReturnType();
  }
 else   if (expressionTree instanceof JCIdent) {
    JCIdent methodCall=(JCIdent)expressionTree;
    return methodCall.type.getReturnType();
  }
 else   if (expressionTree instanceof JCMethodInvocation) {
    return getReturnType(((JCMethodInvocation)expressionTree).getMethodSelect());
  }
 else   if (expressionTree instanceof JCMemberReference) {
    return ((JCMemberReference)expressionTree).sym.type.getReturnType();
  }
  throw new IllegalArgumentException("Expected a JCFieldAccess or JCIdent");
}
