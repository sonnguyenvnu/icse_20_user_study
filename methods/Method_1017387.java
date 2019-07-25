@Nullable @Override public PsiElement resolve(){
  String contents=element.getContents();
  if (StringUtils.isBlank(contents)) {
    return null;
  }
  PsiElement[] methods=RouteHelper.getMethodsOnControllerShortcut(this.element.getProject(),contents);
  if (methods.length > 0) {
    return methods[0];
  }
  return null;
}
