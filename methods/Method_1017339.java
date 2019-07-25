@Override public void invoke(@NotNull Project project,Editor editor,@NotNull PsiElement psiElement) throws IncorrectOperationException {
  PsiElement parent=psiElement.getParent();
  if (!(parent instanceof StringLiteralExpression)) {
    return;
  }
  try {
    FormUtil.replaceFormStringAliasWithClassConstant((StringLiteralExpression)parent);
  }
 catch (  Exception e) {
    HintManager.getInstance().showErrorHint(editor,e.getMessage());
  }
}
