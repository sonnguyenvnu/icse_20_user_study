@Override public void process() throws Exception {
  for (  PipelineStage pipelineStage : stageList) {
    pipelineStage.process();
  }
}
