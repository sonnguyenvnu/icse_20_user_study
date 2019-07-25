@Override public void customize(Build build){
  build.setGroup(this.projectDescription.getGroupId());
  build.setArtifact(this.projectDescription.getArtifactId());
  build.setVersion(this.projectDescription.getVersion());
  this.projectDescription.getRequestedDependencies().forEach((id,dependency) -> build.dependencies().add(id,dependency));
}
