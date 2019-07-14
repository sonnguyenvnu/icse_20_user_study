private StageDeclaration createBuildStageDeclaration(final List<Project> createdProjects,final int version){
  return StageDeclaration.builder().name("Build").actions(createdProjects.stream().map(action -> createBuildActionDeclaration(action,version)).collect(Collectors.toList())).build();
}
