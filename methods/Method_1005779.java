/** 
 * Create an External Dependency
 * @param group group of the dependency
 * @param name name of the dependency
 * @param version version of the dependency
 * @param dependencyFile file of the dependency
 * @param externalDependenciesExtension External Dependency Extension
 * @param jetifierExtension Jetifier Extension
 * @return External Dependency
 */
public static ExternalDependency from(String group,String name,String version,File dependencyFile,@Nullable File dependencySourceFile,ExternalDependenciesExtension externalDependenciesExtension,JetifierExtension jetifierExtension){
  String classifier=DependencyUtils.getModuleClassifier(dependencyFile.getName(),version);
  if (isLocalDependency(dependencyFile.getAbsolutePath())) {
    return new LocalExternalDependency(group,name,version,classifier,dependencyFile,dependencySourceFile,externalDependenciesExtension,jetifierExtension);
  }
  return new ExternalDependency(group,name,version,classifier,dependencyFile,dependencySourceFile,externalDependenciesExtension,jetifierExtension);
}
