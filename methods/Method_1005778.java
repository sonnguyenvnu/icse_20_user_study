/** 
 * Use this method to populate dependency caches of tools/languages etc. This is not meant to be used across multiple threads/gradle task executions which can run in parallel. This method is fully synchronous.
 * @param configuration The configuration to materialize into the dependency cache
 */
public Set<ExternalDependency> build(Configuration configuration){
  OkBuckExtension okBuckExtension=ProjectUtil.getOkBuckExtension(rootProject);
  ExternalDependenciesExtension externalDependenciesExtension=okBuckExtension.getExternalDependenciesExtension();
  JetifierExtension jetifierExtension=okBuckExtension.getJetifierExtension();
  addDependencies(configuration.getAllDependencies());
  return DependencyUtils.resolveExternal(rootProject,configuration,externalDependenciesExtension,jetifierExtension).stream().map(this::get).collect(Collectors.toSet());
}
