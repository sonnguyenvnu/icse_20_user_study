/** 
 * Extracts the "argument name," as defined in section 2.1 of "Nomen est Omen," from the expression. This translates a potentially complex expression into a simple name that can be used by the similarity metric. <p>"Nomen est Omen: Exploring and Exploiting Similarities between Argument and Parameter Names," ICSE 2016
 */
@Nullable private static String extractArgumentName(ExpressionTree expr){
switch (expr.getKind()) {
case TYPE_CAST:
    return extractArgumentName(((TypeCastTree)expr).getExpression());
case MEMBER_SELECT:
{
    MemberSelectTree memberSelect=(MemberSelectTree)expr;
    String member=memberSelect.getIdentifier().toString();
    return member.equals("get") ? extractArgumentName(memberSelect.getExpression()) : member;
  }
case METHOD_INVOCATION:
{
  Symbol sym=getSymbol(expr);
  if (sym == null) {
    return null;
  }
  String methodName=sym.getSimpleName().toString();
  return methodName.equals("get") ? extractArgumentName(((MethodInvocationTree)expr).getMethodSelect()) : methodName;
}
case IDENTIFIER:
{
IdentifierTree idTree=(IdentifierTree)expr;
if (idTree.getName().contentEquals("this")) {
  Symbol sym=getSymbol(idTree);
  return (sym == null) ? null : enclosingClass(sym).getSimpleName().toString();
}
 else {
  return ((IdentifierTree)expr).getName().toString();
}
}
default :
return null;
}
}
