/** 
 * Returns true if this method invocation is of the form  {@code super.foo()} 
 */
private static boolean isSuperCall(Type type,MethodInvocationTree tree,VisitorState state){
  if (tree.getMethodSelect().getKind() == Kind.MEMBER_SELECT) {
    MemberSelectTree select=(MemberSelectTree)tree.getMethodSelect();
    if (select.getExpression().getKind() == Kind.IDENTIFIER) {
      IdentifierTree ident=(IdentifierTree)select.getExpression();
      return ident.getName().contentEquals("super");
    }
 else     if (select.getExpression().getKind() == Kind.MEMBER_SELECT) {
      MemberSelectTree subSelect=(MemberSelectTree)select.getExpression();
      return subSelect.getIdentifier().contentEquals("super") && ASTHelpers.isSameType(ASTHelpers.getType(subSelect.getExpression()),type,state);
    }
  }
  return false;
}
