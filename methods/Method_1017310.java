@NotNull @Override protected Collection<? extends PsiElement> compute(){
  Set<String> serviceNames=ContainerCollectionResolver.ServiceCollector.create(project).convertClassNameToServices(fqnClass);
  if (serviceNames.size() == 0) {
    return Collections.emptyList();
  }
  Collection<PsiElement> psiElements=new ArrayList<>();
  for (  String serviceName : serviceNames) {
    psiElements.addAll(ServiceIndexUtil.findServiceDefinitions(project,serviceName));
  }
  return psiElements;
}
