private List<Project> createBuildProjects(){
  return parallelBuildActions.stream().map(this::createCodeBuildProjectRequest).peek(project -> log.info(project.toString())).map(codeBuild::createProject).map(CreateProjectResponse::project).collect(Collectors.toList());
}
