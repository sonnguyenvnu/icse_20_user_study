@Override public void customize(HelpDocument document){
  this.projectDescription.getRequestedDependencies().forEach((id,dependency) -> {
    Dependency dependencyMetadata=this.metadata.getDependencies().get(id);
    if (dependencyMetadata != null) {
      handleDependency(document,dependencyMetadata);
    }
  }
);
}
