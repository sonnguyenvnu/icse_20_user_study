@SuppressWarnings("NullableProblems") @Override public void apply(Project project){
  verifyGradleVersion();
  createExtension(project);
  Configuration bootArchives=createBootArchivesConfiguration(project);
  registerPluginActions(project,bootArchives);
  unregisterUnresolvedDependenciesAnalyzer(project);
}
