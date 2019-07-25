public static Provisioner create(ArtifactResolver artifactResolver){
  return artifactResolver::resolve;
}
