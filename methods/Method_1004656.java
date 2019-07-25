public static Builder from(Dependency dependency){
  return new Builder(dependency.getGroupId(),dependency.getArtifactId()).initialize(dependency);
}
