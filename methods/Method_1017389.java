/** 
 * A array value inside array and method reference $menu->addChild([ 'route' => '<caret>', ]);
 */
@Nullable public static Result match(@NotNull PsiElement psiElement,@NotNull Matcher matcher){
  PsiElement arrayValue=psiElement.getParent();
  if (PsiElementAssertUtil.isNotNullAndIsElementType(arrayValue,PhpElementTypes.ARRAY_VALUE)) {
    PsiElement arrayHashElement=arrayValue.getParent();
    if (arrayHashElement instanceof ArrayHashElement) {
      PhpPsiElement arrayKey=((ArrayHashElement)arrayHashElement).getKey();
      if (arrayKey instanceof StringLiteralExpression && ArrayUtils.contains(matcher.getArrayKeys(),((StringLiteralExpression)arrayKey).getContents())) {
        PsiElement arrayCreationExpression=arrayHashElement.getParent();
        if (arrayCreationExpression instanceof ArrayCreationExpression) {
          Pair<PhpMethodReferenceCall,MethodReference> matchPair=matchesMethodCall(arrayCreationExpression,matcher.getMethodCalls());
          if (matchPair != null) {
            return new Result(matchPair.getFirst(),matchPair.getSecond(),(StringLiteralExpression)arrayKey);
          }
        }
      }
    }
  }
  return null;
}
