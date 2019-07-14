/** 
 * getHighLightedFlows
 * @param processDefinition
 * @param processInstanceId
 * @return
 */
private List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinition,String processInstanceId){
  List<String> highLightedFlows=new ArrayList<String>();
  List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
  LinkedList<HistoricActivityInstance> hisActInstList=new LinkedList<HistoricActivityInstance>();
  hisActInstList.addAll(historicActivityInstances);
  getHighlightedFlows(processDefinition.getActivities(),hisActInstList,highLightedFlows);
  return highLightedFlows;
}
