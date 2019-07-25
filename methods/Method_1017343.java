@Override public void invoke(@NotNull Project project,Editor editor,@NotNull PsiElement psiElement) throws IncorrectOperationException {
  XmlTag xmlTag=getServiceTagValid(psiElement);
  if (xmlTag == null) {
    return;
  }
  final List<String> args=ServiceActionUtil.getXmlMissingArgumentTypes(xmlTag,true,new ContainerCollectionResolver.LazyServiceCollector(project));
  if (args.size() == 0) {
    return;
  }
  ServiceActionUtil.fixServiceArgument(args,xmlTag);
}
