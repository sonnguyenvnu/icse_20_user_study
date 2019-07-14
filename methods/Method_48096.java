private StageDeclaration createSourceStageDeclaration(int version){
  Preconditions.checkArgument(version > 0);
  return StageDeclaration.builder().name("Source").actions(ActionDeclaration.builder().name(pipelineName).actionTypeId(ActionTypeId.builder().category(ActionCategory.Source).owner(ActionOwner.ThirdParty).provider("GitHub").version(Integer.toString(version)).build()).outputArtifacts(OutputArtifact.builder().name(sourceOutputArtifactName).build()).configuration(ImmutableMap.of("Owner",githubOwner,"Repo",githubRepo,"Branch",githubBranch,"OAuthToken",githubToken)).runOrder(1).build()).build();
}
