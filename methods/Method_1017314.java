@NotNull public Collection<String> collect(@NotNull PsiElement psiElement,@NotNull Collection<ContainerService> serviceMap){
  return ServiceSuggestionUtil.createSuggestions(ServiceContainerUtil.getYamlConstructorTypeHint(psiElement,new ContainerCollectionResolver.LazyServiceCollector(psiElement.getProject())),serviceMap);
}
