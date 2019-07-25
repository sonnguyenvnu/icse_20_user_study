@Contract(pure=true) @NotNull public static PsiElement qualifier(@NotNull final Qualified qualified){
  return qualified.getFirstChild();
}
