@Override public PipelineContext appendStages(List<PipelineStage> stages){
  for (  PipelineStage pipelineStage : stages) {
    appendStage(pipelineStage);
  }
  return this;
}
