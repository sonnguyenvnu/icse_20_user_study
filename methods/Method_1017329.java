public void register(@NotNull GotoCompletionRegistrarParameter registrar){
  registrar.register(PhpElementsUtil.getParameterListArrayValuePattern(),psiElement -> {
    PsiElement context=psiElement.getContext();
    if (!(context instanceof StringLiteralExpression)) {
      return null;
    }
    MethodMatcher.MethodMatchParameter methodMatchParameter=new MethodMatcher.ArrayParameterMatcher(context,0).withSignature("\\Doctrine\\Common\\Persistence\\ObjectRepository","findOneBy").withSignature("\\Doctrine\\Common\\Persistence\\ObjectRepository","findBy").match();
    if (methodMatchParameter != null) {
      MethodReference methodReference=methodMatchParameter.getMethodReference();
      Collection<PhpClass> phpClasses=PhpElementsUtil.getClassFromPhpTypeSetArrayClean(psiElement.getProject(),methodReference.getType().getTypes());
      if (phpClasses.size() == 0) {
        PhpExpression classReference=methodReference.getClassReference();
        if (classReference != null) {
          PhpType type=classReference.getType();
          for (          String s : type.getTypes()) {
            if (PhpType.isUnresolved(s)) {
              continue;
            }
            for (            DoctrineModelInterface doctrineModel : DoctrineMetadataUtil.findMetadataModelForRepositoryClass(psiElement.getProject(),s)) {
              phpClasses.addAll(PhpElementsUtil.getClassesInterface(psiElement.getProject(),doctrineModel.getClassName()));
            }
          }
        }
      }
      if (phpClasses.size() == 0) {
        return null;
      }
      return new MyArrayFieldMetadataGotoCompletionRegistrar(psiElement,phpClasses);
    }
    return null;
  }
);
}
