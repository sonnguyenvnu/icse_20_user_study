@Override public void customize(Build build){
  if (!this.buildMetadataResolver.hasFacet(build,"web")) {
    Dependency dependency=determineWebDependency(this.metadata);
    build.dependencies().add(dependency.getId(),MetadataBuildItemMapper.toDependency(dependency));
  }
  Dependency tomcat=new Dependency().asSpringBootStarter("tomcat");
  tomcat.setScope(Dependency.SCOPE_PROVIDED);
  build.dependencies().add("tomcat",MetadataBuildItemMapper.toDependency(tomcat));
}
