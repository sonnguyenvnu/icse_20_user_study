@Contract(pure=true) @Nullable private static Quotable operand(@NotNull PsiElement[] children,int operatorIndex){
  int operandCount=children.length - 1 - operatorIndex;
  Quotable operand=null;
  if (operandCount == 1) {
    PsiElement child=children[operatorIndex + 1];
    if (child instanceof Quotable) {
      operand=(Quotable)child;
    }
  }
  return operand;
}
