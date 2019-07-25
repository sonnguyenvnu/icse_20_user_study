public final ExternalDependency get(ExternalDependency externalDependency){
  LOG.info("Requested dependency {}",externalDependency);
  ExternalDependency dependency=forcedDeps.getOrDefault(externalDependency.getVersionless(),externalDependency);
  LOG.info("Picked dependency {}",dependency);
  dependencyManager.addDependency(dependency,skipPrebuilt);
  return dependency;
}
