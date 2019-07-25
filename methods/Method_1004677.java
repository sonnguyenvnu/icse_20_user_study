@Override public void customize(GradleBuild build){
  boolean providedRuntimeUsed=build.dependencies().items().anyMatch((dependency) -> DependencyScope.PROVIDED_RUNTIME.equals(dependency.getScope()));
  boolean war=build.getPlugins().stream().anyMatch((plugin) -> plugin.getId().equals("war"));
  if (providedRuntimeUsed && !war) {
    build.addConfiguration("providedRuntime");
  }
}
