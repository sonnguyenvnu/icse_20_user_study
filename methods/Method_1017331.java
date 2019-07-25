@Override public void register(@NotNull GotoCompletionRegistrarParameter registrar){
  registrar.register(PhpElementsUtil.getParameterInsideMethodReferencePattern(),psiElement -> {
    PsiElement context=psiElement.getContext();
    if (!(context instanceof StringLiteralExpression)) {
      return null;
    }
    if (!isTableNameRegistrar(context)) {
      return null;
    }
    return new DbalTableGotoCompletionProvider(context);
  }
);
  registrar.register(PlatformPatterns.psiElement().withParent(StringLiteralExpression.class).withLanguage(PhpLanguage.INSTANCE),psiElement -> {
    PsiElement context=psiElement.getContext();
    if (!(context instanceof StringLiteralExpression)) {
      return null;
    }
    MethodMatcher.MethodMatchParameter methodMatchParameter=new MethodMatcher.ArrayParameterMatcher(context,1).withSignature("\\Doctrine\\DBAL\\Connection","insert").withSignature("\\Doctrine\\DBAL\\Connection","update").match();
    if (methodMatchParameter == null) {
      return null;
    }
    PsiElement[] parameters=methodMatchParameter.getParameters();
    if (parameters.length < 2) {
      return null;
    }
    String stringValue=PhpElementsUtil.getStringValue(parameters[0]);
    if (StringUtils.isBlank(stringValue)) {
      return null;
    }
    return new DbalFieldGotoCompletionProvider(context,stringValue);
  }
);
  registrar.register(PhpElementsUtil.getParameterInsideMethodReferencePattern(),psiElement -> {
    PsiElement context=psiElement.getContext();
    if (!(context instanceof StringLiteralExpression)) {
      return null;
    }
    MethodMatcher.MethodMatchParameter methodMatchParameter=new MethodMatcher.StringParameterRecursiveMatcher(context,2).withSignature("Doctrine\\DBAL\\Query\\QueryBuilder","innerJoin").withSignature("Doctrine\\DBAL\\Query\\QueryBuilder","leftJoin").withSignature("Doctrine\\DBAL\\Query\\QueryBuilder","join").withSignature("Doctrine\\DBAL\\Query\\QueryBuilder","rightJoin").match();
    if (methodMatchParameter == null) {
      return null;
    }
    PsiElement[] parameters=methodMatchParameter.getParameters();
    if (parameters.length < 2) {
      return null;
    }
    String stringValue=PhpElementsUtil.getStringValue(parameters[1]);
    if (StringUtils.isBlank(stringValue)) {
      return null;
    }
    return new MyDbalAliasGotoCompletionProvider(context,stringValue,PhpElementsUtil.getStringValue(parameters[0]));
  }
);
}
