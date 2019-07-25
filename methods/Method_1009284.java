@Contract(pure=true) @Nullable public static Quotable operand(@NotNull Prefix prefix){
  PsiElement[] children=prefix.getChildren();
  int operatorIndex=operatorIndex(children);
  return operand(children,operatorIndex);
}
