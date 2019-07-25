private void initialize(){
  collectModulesAndInjected();
  dependencies=collectDependencyMap();
  logger.n("dependencies: %s",dependencies);
  logger.n("internalDependencies: %s",internalDependencyMap);
}
