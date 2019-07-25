/** 
 * \Symfony\Component\EventDispatcher\EventSubscriberInterface::getSubscribedEvents return array( 'pre.foo' => array('preFoo', 10), 'post.foo' => array('postFoo'), ');
 */
public void register(@NotNull GotoCompletionRegistrarParameter registrar){
  registrar.register(PlatformPatterns.psiElement().withParent(StringLiteralExpression.class).withLanguage(PhpLanguage.INSTANCE),psiElement -> {
    PsiElement parent=psiElement.getParent();
    if (!(parent instanceof StringLiteralExpression)) {
      return null;
    }
    PsiElement arrayValue=parent.getParent();
    if (arrayValue != null && arrayValue.getNode().getElementType() == PhpElementTypes.ARRAY_VALUE) {
      PhpReturn phpReturn=PsiTreeUtil.getParentOfType(arrayValue,PhpReturn.class);
      if (phpReturn != null) {
        Method method=PsiTreeUtil.getParentOfType(arrayValue,Method.class);
        if (method != null) {
          String name=method.getName();
          if ("getSubscribedEvents".equals(name)) {
            PhpClass containingClass=method.getContainingClass();
            if (containingClass != null && PhpElementsUtil.isInstanceOf(containingClass,"\\Symfony\\Component\\EventDispatcher\\EventSubscriberInterface")) {
              return new PhpClassPublicMethodProvider(containingClass);
            }
          }
        }
      }
    }
    return null;
  }
);
}
