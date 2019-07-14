private void addConfigurationArtifacts(String[] configurations,Set<URL> urls) throws IOException {
  for (  String configuration : configurations) {
    getLogger().debug("Adding configuration to classpath: " + configuration);
    ResolvedConfiguration resolvedConfiguration=getProject().getConfigurations().getByName(configuration).getResolvedConfiguration();
    for (    ResolvedArtifact artifact : resolvedConfiguration.getResolvedArtifacts()) {
      URL artifactUrl=artifact.getFile().toURI().toURL();
      getLogger().debug("Adding artifact to classpath: " + artifactUrl);
      urls.add(artifactUrl);
    }
  }
}
