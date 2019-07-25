@Override public void register(@NotNull GotoCompletionRegistrarParameter registrar){
  registrar.register(XmlPatterns.psiElement().withParent(XmlHelper.getImportResourcePattern()),ImportResourceGotoCompletionProvider::new);
  registrar.register(XmlPatterns.psiElement().withParent(XmlHelper.getServiceIdAttributePattern()),ServiceIdCompletionProvider::new);
  registrar.register(XmlPatterns.psiElement().withParent(XmlHelper.getServiceAliasPattern()),ServiceAliasCompletionProvider::new);
  registrar.register(XmlPatterns.psiElement().withParent(XmlHelper.getTagAttributePattern("factory","method").inside(XmlHelper.getInsideTagPattern("services")).inFile(XmlHelper.getXmlFilePattern())),ServiceFactoryMethodCompletionProvider::new);
  registrar.register(XmlHelper.getRouteDefaultWithKeyAttributePattern("route"),RouteGotoCompletionProvider::new);
  registrar.register(XmlHelper.getRouteDefaultWithKeyAttributePattern("template"),TemplateGotoCompletionRegistrar::new);
  registrar.register(XmlPatterns.psiElement().withParent(XmlHelper.getTagAttributePattern("service","decorates").inside(XmlHelper.getInsideTagPattern("services")).inFile(XmlHelper.getXmlFilePattern())),MyDecoratedServiceCompletionProvider::new);
  registrar.register(XmlPatterns.psiElement().withParent(XmlHelper.getGlobalStringAttributePattern()),new MyGlobalStringTemplateGotoCompletionContributor());
  registrar.register(XmlPatterns.psiElement().withParent(XmlHelper.getAttributePattern("template")),MyTemplateCompletionRegistrar::new);
  registrar.register(XmlHelper.getTagAttributePattern("argument","key"),MyKeyArgumentGotoCompletionProvider::new);
}
