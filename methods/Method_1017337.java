public void register(@NotNull GotoCompletionRegistrarParameter registrar){
  registrar.register(PlatformPatterns.psiElement().withParent(StringLiteralExpression.class).withLanguage(PhpLanguage.INSTANCE),psiElement -> {
    PsiElement parent=psiElement.getParent();
    if (parent == null) {
      return null;
    }
    MethodMatcher.MethodMatchParameter methodMatchParameter=new MethodMatcher.StringParameterMatcher(parent,1).withSignature(FormUtil.PHP_FORM_BUILDER_SIGNATURES).match();
    if (methodMatchParameter == null) {
      return null;
    }
    return new FormBuilderAddGotoCompletionProvider(parent);
  }
);
  registrar.register(PlatformPatterns.psiElement().withParent(StringLiteralExpression.class).withLanguage(PhpLanguage.INSTANCE),psiElement -> {
    PsiElement parent=psiElement.getParent();
    if (!(parent instanceof StringLiteralExpression)) {
      return null;
    }
    MethodMatcher.MethodMatchParameter methodMatchParameter=new MethodMatcher.ArrayParameterMatcher(parent,3).withSignature("\\Symfony\\Component\\Form\\FormFactoryInterface","createNamedBuilder").withSignature("\\Symfony\\Component\\Form\\FormFactoryInterface","createNamed").match();
    if (methodMatchParameter == null) {
      return null;
    }
    return getFormProvider((StringLiteralExpression)parent,methodMatchParameter.getParameters()[1]);
  }
);
  registrar.register(PlatformPatterns.psiElement().withParent(StringLiteralExpression.class).withLanguage(PhpLanguage.INSTANCE),psiElement -> {
    PsiElement parent=psiElement.getParent();
    if (!(parent instanceof StringLiteralExpression)) {
      return null;
    }
    MethodMatcher.MethodMatchParameter methodMatchParameter=new MethodMatcher.ArrayParameterMatcher(parent,2).withSignature("\\Symfony\\Bundle\\FrameworkBundle\\Controller\\Controller","createForm").withSignature("\\Symfony\\Bundle\\FrameworkBundle\\Controller\\ControllerTrait","createForm").withSignature("\\Symfony\\Component\\Form\\FormFactoryInterface","create").withSignature("\\Symfony\\Component\\Form\\FormFactory","createBuilder").match();
    if (methodMatchParameter == null) {
      return null;
    }
    return getFormProvider((StringLiteralExpression)parent,methodMatchParameter.getParameters()[0]);
  }
);
  registrar.register(PlatformPatterns.psiElement().withParent(StringLiteralExpression.class).withLanguage(PhpLanguage.INSTANCE),psiElement -> {
    PsiElement parent=psiElement.getParent();
    if (!(parent instanceof StringLiteralExpression) || !PhpElementsUtil.getMethodReturnPattern().accepts(parent)) {
      return null;
    }
    Method method=PsiTreeUtil.getParentOfType(psiElement,Method.class);
    if (method == null) {
      return null;
    }
    if (!PhpElementsUtil.isMethodInstanceOf(method,"\\Symfony\\Component\\Form\\FormTypeInterface","getParent")) {
      return null;
    }
    return new FormBuilderAddGotoCompletionProvider(parent);
  }
);
  registrar.register(PlatformPatterns.psiElement().withParent(StringLiteralExpression.class).withLanguage(PhpLanguage.INSTANCE),psiElement -> {
    PsiElement parent=psiElement.getParent();
    if (!(parent instanceof StringLiteralExpression)) {
      return null;
    }
    MethodMatcher.MethodMatchParameter methodMatchParameter=new MethodMatcher.StringParameterMatcher(parent,1).withSignature("\\Symfony\\Component\\Form\\FormFactoryInterface","createNamedBuilder").withSignature("\\Symfony\\Component\\Form\\FormFactoryInterface","createNamed").match();
    if (methodMatchParameter == null) {
      return null;
    }
    return new FormBuilderAddGotoCompletionProvider(parent);
  }
);
  registrar.register(PhpPsiMatcher.ArrayValueWithKeyAndMethod.pattern().withLanguage(PhpLanguage.INSTANCE),psiElement -> {
    PsiElement parent=psiElement.getParent();
    if (!(parent instanceof StringLiteralExpression)) {
      return null;
    }
    PhpPsiMatcher.ArrayValueWithKeyAndMethod.Result result=PhpPsiMatcher.match(parent,CHOICE_TRANSLATION_DOMAIN_MATCHER);
    if (result == null) {
      return null;
    }
    return new TranslationDomainGotoCompletionProvider(psiElement);
  }
);
  registrar.register(PlatformPatterns.psiElement(),psiElement -> {
    PsiElement parent=psiElement.getParent();
    if (!(parent instanceof StringLiteralExpression)) {
      return null;
    }
    PsiElement choicesArrayValue1=parent.getParent();
    if (choicesArrayValue1.getNode().getElementType() == PhpElementTypes.ARRAY_VALUE) {
      PsiElement parent1=choicesArrayValue1.getParent();
      if (parent1 instanceof ArrayHashElement) {
        PsiElement choices=parent1.getParent();
        if (choices instanceof ArrayCreationExpression) {
          return createTranslationGotoCompletionWithLabelSwitch(psiElement,(ArrayCreationExpression)choices,arrayCreationExpression -> {
            PhpPsiElement value=PhpElementsUtil.getArrayValue(arrayCreationExpression,"choices_as_values");
            if (value == null) {
              return SymfonyUtil.isVersionLessThenEquals(arrayCreationExpression.getProject(),"2.7");
            }
            return !(value instanceof ConstantReference && "false".equalsIgnoreCase(value.getName()));
          }
);
        }
      }
    }
    ArrayCreationExpression choices=PhpElementsUtil.getCompletableArrayCreationElement(parent);
    if (choices != null) {
      return createTranslationGotoCompletionWithLabelSwitch(psiElement,choices,arrayCreationExpression -> {
        PhpPsiElement value=PhpElementsUtil.getArrayValue(arrayCreationExpression,"choices_as_values");
        return !(value instanceof ConstantReference && "false".equalsIgnoreCase(value.getName()));
      }
);
    }
    return null;
  }
);
}
