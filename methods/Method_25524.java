/** 
 * Returns the receiver of an expression. <p>Examples: <pre> {@code a.foo() ==> a a.b.foo() ==> a.b a.bar().foo() ==> a.bar() a.b.c ==> a.b a.b().c ==> a.b() this.foo() ==> this foo() ==> null TheClass.aStaticMethod() ==> TheClass aStaticMethod() ==> null aStaticallyImportedMethod() ==> null}</pre>
 */
@Nullable public static ExpressionTree getReceiver(ExpressionTree expressionTree){
  if (expressionTree instanceof MethodInvocationTree) {
    ExpressionTree methodSelect=((MethodInvocationTree)expressionTree).getMethodSelect();
    if (methodSelect instanceof IdentifierTree) {
      return null;
    }
    return getReceiver(methodSelect);
  }
 else   if (expressionTree instanceof MemberSelectTree) {
    return ((MemberSelectTree)expressionTree).getExpression();
  }
 else   if (expressionTree instanceof MemberReferenceTree) {
    return ((MemberReferenceTree)expressionTree).getQualifierExpression();
  }
 else {
    throw new IllegalStateException(String.format("Expected expression '%s' to be a method invocation or field access, but was %s",expressionTree,expressionTree.getKind()));
  }
}
