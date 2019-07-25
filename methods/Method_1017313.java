@NotNull public Collection<String> collect(@NotNull PsiElement psiElement,@NotNull Collection<ContainerService> serviceMap){
  if (!(psiElement.getContainingFile() instanceof XmlFile) || psiElement.getNode().getElementType() != XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN) {
    return Collections.emptyList();
  }
  return ServiceSuggestionUtil.createSuggestions(ServiceContainerUtil.getXmlConstructorTypeHint(psiElement,new ContainerCollectionResolver.LazyServiceCollector(psiElement.getProject())),serviceMap);
}
