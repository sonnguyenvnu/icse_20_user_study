@Override public void register(@NotNull GotoCompletionRegistrarParameter registrar){
  registrar.register(PhpPsiMatcher.ArrayValueWithKeyAndNewExpression.pattern(),psiElement -> {
    PsiElement parent=psiElement.getParent();
    if (!(parent instanceof StringLiteralExpression)) {
      return null;
    }
    PhpPsiMatcher.ArrayValueWithKeyAndNewExpression.Result result=PhpPsiMatcher.match(parent,CONSTRAINT_MESSAGE);
    if (result == null) {
      return null;
    }
    return new TranslationGotoCompletionProvider(psiElement,"validators");
  }
);
  registrar.register(PlatformPatterns.psiElement().withParent(PhpElementsUtil.getMethodWithFirstStringPattern()),psiElement -> {
    PsiElement context=psiElement.getContext();
    if (!(context instanceof StringLiteralExpression)) {
      return null;
    }
    MethodMatcher.MethodMatchParameter methodMatchParameter=new MethodMatcher.StringParameterRecursiveMatcher(context,0).withSignature("Symfony\\Component\\Validator\\Context\\ExecutionContextInterface","addViolation").withSignature("Symfony\\Component\\Validator\\Context\\ExecutionContextInterface","buildViolation").match();
    if (methodMatchParameter == null) {
      return null;
    }
    return new TranslationGotoCompletionProvider(psiElement,"validators");
  }
);
  registrar.register(PlatformPatterns.psiElement().withParent(PhpElementsUtil.getMethodWithFirstStringPattern()),psiElement -> {
    PsiElement context=psiElement.getContext();
    if (!(context instanceof StringLiteralExpression)) {
      return null;
    }
    MethodMatcher.MethodMatchParameter methodMatchParameter=new MethodMatcher.StringParameterRecursiveMatcher(context,0).withSignature("\\Symfony\\Component\\Validator\\Violation\\ConstraintViolationBuilderInterface","setTranslationDomain").match();
    if (methodMatchParameter == null) {
      return null;
    }
    return new TranslationDomainGotoCompletionProvider(psiElement);
  }
);
}
