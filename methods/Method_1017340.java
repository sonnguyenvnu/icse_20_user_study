@Nullable @Override public PsiElement resolve(){
  MethodReference[] formBuilderTypes=FormUtil.getFormBuilderTypes(method);
  for (  MethodReference methodReference : formBuilderTypes) {
    String fieldName=PsiElementUtils.getMethodParameterAt(methodReference,0);
    if (fieldName != null && fieldName.equals(this.element.getContents())) {
      return methodReference;
    }
  }
  return null;
}
