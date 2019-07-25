@Nullable @Override public PsiElement resolve(){
  final String escaped=PathExpressionUtil.escape(originalRefValue);
  final String pathExpression=String.format("$.components.schemas.%s",escaped);
  final PsiFile psiFile=getElement().getContainingFile();
  return new PathFinder().findByPathFrom(pathExpression,psiFile).orElse(null);
}
