/** 
 * Matches when the receiver of an instance method is the same reference as a particular argument to the method. For example, receiverSameAsArgument(1) would match  {@code obj.method("", obj)}
 * @param argNum The number of the argument to compare against (zero-based.
 */
public static Matcher<? super MethodInvocationTree> receiverSameAsArgument(final int argNum){
  return (t,state) -> {
    List<? extends ExpressionTree> args=t.getArguments();
    if (args.size() <= argNum) {
      return false;
    }
    ExpressionTree arg=args.get(argNum);
    JCExpression methodSelect=(JCExpression)t.getMethodSelect();
    if (methodSelect instanceof JCFieldAccess) {
      JCFieldAccess fieldAccess=(JCFieldAccess)methodSelect;
      return ASTHelpers.sameVariable(fieldAccess.getExpression(),arg);
    }
 else     if (methodSelect instanceof JCIdent) {
      return "this".equals(arg.toString());
    }
    return false;
  }
;
}
