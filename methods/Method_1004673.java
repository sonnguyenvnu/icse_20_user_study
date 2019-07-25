@Override public void customize(Build build){
  boolean hasStarter=this.buildResolver.dependencies(build).anyMatch(this::isValidStarter);
  if (!hasStarter) {
    Dependency root=new Dependency();
    root.setId(DEFAULT_STARTER);
    root.asSpringBootStarter("");
    build.dependencies().add(DEFAULT_STARTER,MetadataBuildItemMapper.toDependency(root));
  }
}
