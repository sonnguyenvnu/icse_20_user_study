private List<PipelineStage> getPipelineStagesInApplicationContext(ApplicationContext applicationContext){
  Map<String,PipelineStage> beanName2PipelineStage=applicationContext.getBeansOfType(PipelineStage.class);
  Map<String,PipelineStage> pipelineName2PipelineStage=new HashMap<>();
  for (  Map.Entry<String,PipelineStage> beanName2PipelineStageEntry : beanName2PipelineStage.entrySet()) {
    String pipelineName=beanName2PipelineStageEntry.getValue().getName();
    PipelineStage oldValue=pipelineName2PipelineStage.get(pipelineName);
    if (oldValue == null || oldValue.getPriority() < beanName2PipelineStageEntry.getValue().getPriority()) {
      pipelineName2PipelineStage.put(pipelineName,beanName2PipelineStageEntry.getValue());
    }
  }
  List<PipelineStage> pipelineStages=new ArrayList<>(pipelineName2PipelineStage.values());
  OrderComparator.sort(pipelineStages);
  return pipelineStages;
}
