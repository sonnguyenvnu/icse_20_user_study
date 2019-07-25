@Nullable @Override public PsiElement resolve(){
  final String pathExpression=Arrays.stream(originalRefValue.substring(2,originalRefValue.length()).split("/")).map(PathExpressionUtil::escape).collect(Collectors.joining(".","$.",""));
  final PsiFile psiFile=getElement().getContainingFile();
  return new PathFinder().findByPathFrom(pathExpression,psiFile).orElse(null);
}
