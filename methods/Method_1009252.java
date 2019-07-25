@Nullable private static PsiElement head(@NotNull ElixirMatchedWhenOperation whenOperation){
  PsiElement head=null;
  PsiElement parameterizedType=whenOperation.leftOperand();
  if (parameterizedType instanceof org.elixir_lang.psi.operation.Type) {
    head=head((org.elixir_lang.psi.operation.Type)parameterizedType);
  }
  return head;
}
