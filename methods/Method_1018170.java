/** 
 * Return the map of Processor Name or ID (defaults to Name based upon this.key) to the list of downstream processors
 * @return
 */
public Map<String,List<NifiFlowCacheBaseProcessorDTO>> build(){
  List<ConnectionDTO> connectionDTOS=new ArrayList<>();
  Set<ProcessorDTO> processorDTOS=NifiProcessUtil.getProcessors(template);
  template.getSnippet().getProcessGroups().stream().forEach(group -> {
    connectionDTOS.addAll(NifiConnectionUtil.getAllConnections(group));
  }
);
  connectionDTOS.addAll(template.getSnippet().getConnections());
  processorDTOS.stream().forEach(p -> {
    processorIdToProcessorName.put(p.getId(),p.getName());
  }
);
  addConnectionSourceToDestination(connectionDTOS);
  List<String> inputProcessorIds=NifiConnectionUtil.getInputProcessorIds(connectionDTOS);
  populateInputProcessorRelations(inputProcessorIds);
  return inputProcessorRelations;
}
