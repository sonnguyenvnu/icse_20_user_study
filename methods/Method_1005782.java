private Set<ExternalDependency> resolved(Collection<ExternalDependency> externalDependencies){
  Configuration detached=project.getConfigurations().detachedConfiguration(externalDependencies.stream().map(ExternalDependency::getAsGradleDependency).toArray(Dependency[]::new));
  return DependencyUtils.resolveExternal(project,detached,externalDependenciesExtension,jetifierExtension);
}
