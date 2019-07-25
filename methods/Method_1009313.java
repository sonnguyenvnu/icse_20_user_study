@Nullable public static Call type(AtUnqualifiedNoParenthesesCall atUnqualifiedNoParenthesesCall){
  PsiElement[] arguments=atUnqualifiedNoParenthesesCall.getNoParenthesesOneArgument().arguments();
  Call type=null;
  if (arguments.length == 1) {
    PsiElement argument=arguments[0];
    if (argument instanceof Call) {
      type=(Call)argument;
    }
  }
  return type;
}
