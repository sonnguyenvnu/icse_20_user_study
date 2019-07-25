@Nullable static PsiElement head(@NotNull Call type){
  PsiElement head=null;
  if (type instanceof org.elixir_lang.psi.operation.Type) {
    head=head((org.elixir_lang.psi.operation.Type)type);
  }
 else   if (type instanceof ElixirMatchedWhenOperation) {
    head=head((ElixirMatchedWhenOperation)type);
  }
  return head;
}
