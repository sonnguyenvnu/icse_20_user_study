@Nullable @Override public PsiElement resolve(){
  return FormUtil.getFormTypeToClass(getElement().getProject(),element.getContents());
}
