public void register(String processorId,String flowFileId){
  Processor processor=processors.getOrDefault(processorId,new Processor(processorId));
  processor.setProcessorId(processorId);
  if (processor.isEmpty()) {
    releaseAll(true);
    processor.setFlowFileId(flowFileId);
    processors.put(processorId,processor);
  }
 else {
    throw new InvalidSavePointId();
  }
}
