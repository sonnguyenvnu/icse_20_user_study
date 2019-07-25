public void parse(File file){
  VirtualFile virtualFile=VfsUtil.findFileByIoFile(file,true);
  if (virtualFile == null) {
    Symfony2ProjectComponent.getLogger().info("VfsUtil missing translation: " + file.getPath());
    return;
  }
  PsiFile psiFile;
  try {
    psiFile=PhpPsiElementFactory.createPsiFileFromText(this.project,StreamUtil.readText(virtualFile.getInputStream(),"UTF-8"));
  }
 catch (  IOException e) {
    return;
  }
  if (psiFile == null) {
    return;
  }
  Symfony2ProjectComponent.getLogger().info("update translations: " + file.getPath());
  Collection<NewExpression> messageCatalogues=PsiTreeUtil.collectElementsOfType(psiFile,NewExpression.class);
  for (  NewExpression newExpression : messageCatalogues) {
    ClassReference classReference=newExpression.getClassReference();
    if (classReference != null) {
      PsiElement constructorMethod=classReference.resolve();
      if (constructorMethod instanceof Method) {
        PhpClass phpClass=((Method)constructorMethod).getContainingClass();
        if (phpClass != null && PhpElementsUtil.isInstanceOf(phpClass,"\\Symfony\\Component\\Translation\\MessageCatalogueInterface")) {
          this.getTranslationMessages(newExpression);
        }
      }
    }
  }
}
