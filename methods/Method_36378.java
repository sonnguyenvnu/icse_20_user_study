@Override public PipelineContext appendStage(PipelineStage stage){
  this.stageList.add(stage);
  return this;
}
