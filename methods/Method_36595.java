private void unregisterUnresolvedDependenciesAnalyzer(Project project){
  UnresolvedDependenciesAnalyzer unresolvedDependenciesAnalyzer=new UnresolvedDependenciesAnalyzer();
  project.getConfigurations().all((configuration) -> {
    ResolvableDependencies incoming=configuration.getIncoming();
    incoming.afterResolve((resolvableDependencies) -> {
      if (incoming.equals(resolvableDependencies)) {
        unresolvedDependenciesAnalyzer.analyze(configuration.getResolvedConfiguration().getLenientConfiguration().getUnresolvedModuleDependencies());
      }
    }
);
  }
);
  project.getGradle().buildFinished((buildResult) -> unresolvedDependenciesAnalyzer.buildFinished(project));
}
