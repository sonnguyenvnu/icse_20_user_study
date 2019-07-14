private ActionDeclaration createBuildActionDeclaration(final Project project,int version){
  return ActionDeclaration.builder().name(project.name()).inputArtifacts(InputArtifact.builder().name(sourceOutputArtifactName).build()).actionTypeId(ActionTypeId.builder().category(ActionCategory.Build).owner(ActionOwner.AWS).provider("CodeBuild").version(Integer.toString(version)).build()).outputArtifacts(OutputArtifact.builder().name(project.artifacts().name()).build()).configuration(ImmutableMap.of("ProjectName",project.name())).runOrder(1).build();
}
