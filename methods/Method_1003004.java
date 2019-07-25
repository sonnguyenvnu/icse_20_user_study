@Override public PsiElement replace(@NotNull PsiElement newElement) throws IncorrectOperationException {
  final PsiClass containingClass=getContainingClass();
  if (null != containingClass) {
    CheckUtil.checkWritable(containingClass);
    return containingClass.add(newElement);
  }
  return null;
}
