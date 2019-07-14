/** 
 * This method assures that the S3 bucket is available, creates individual build projects for each module defined in the input YAML file creates the source and build stages of the pipeline, and finally creates the pipeline.
 */
void run(){
  assureS3Bucket();
  final List<Project> buildProjects=createBuildProjects();
  final int version=1;
  final StageDeclaration sourceStage=createSourceStageDeclaration(version);
  final StageDeclaration buildStage=createBuildStageDeclaration(buildProjects,version);
  final CreatePipelineResponse pipeline=codePipeline.createPipeline(CreatePipelineRequest.builder().pipeline(PipelineDeclaration.builder().roleArn(codePipelineRoleArn).name(pipelineName).version(Integer.MAX_VALUE).stages(sourceStage,buildStage).artifactStore(ArtifactStore.builder().type(ArtifactStoreType.S3).location(s3Bucket).build()).build()).build());
  Preconditions.checkNotNull(pipeline,"created pipeline was null");
}
