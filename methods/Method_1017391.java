/** 
 * new Foo(['message' => '<caret>'])
 */
@Nullable public static ArrayValueWithKeyAndNewExpressionMatcher.Result match(@NotNull PsiElement psiElement,@NotNull ArrayValueWithKeyAndNewExpressionMatcher.Matcher matcher){
  return ArrayValueWithKeyAndNewExpression.match(psiElement,matcher);
}
