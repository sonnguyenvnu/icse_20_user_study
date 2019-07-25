/** 
 * Inspects the process group
 * @return the inspection result with the contents of the process group
 */
public NiFiFlowInspection inspect(){
  NiFiFlowInspection inspection=new NiFiFlowInspection(processGroupId,level,parent,Thread.currentThread().getName());
  long start=System.currentTimeMillis();
  if (retryNumber > 0) {
    log.info("Retry inspection attempt number: {}.  Inspecting process group: {} on thread {} ",retryNumber,processGroupId,Thread.currentThread().getName());
  }
  ProcessGroupFlowDTO flow=null;
  try {
    flow=restClient.processGroups().flow(processGroupId);
    if (retryNumber > 0) {
      log.info("Reattempt was successful.  Successfully inspected process group: {} on thread: {} after {} retry attempts. ",processGroupId,Thread.currentThread().getName(),retryNumber);
    }
  }
 catch (  Exception e) {
    retryNumber++;
    boolean shouldRetry=retryNumber < RETRIES;
    log.warn("Exception while inspecting process group: {} on thread {}. {} ",processGroupId,Thread.currentThread().getName(),shouldRetry ? "The system will attempt to inspect again." : " out of retry attempts.");
    if (retryNumber < RETRIES) {
      log.warn("Retry inspecting process group: {} on thread {}. ",processGroupId,Thread.currentThread().getName());
      inspect();
    }
 else {
      log.error("Unable to inspect process group: {} after {} attempts.  Kylo Operations Manager may have issues processing Jobs/Steps from NiFi. ",processGroupId,retryNumber);
    }
  }
  if (flow != null) {
    inspection.setProcessGroupName(flow.getBreadcrumb().getBreadcrumb().getName());
    flow.getFlow().getProcessGroups().stream().forEach(processGroupEntity -> {
      inspection.addGroupToInspect(processGroupEntity.getId());
    }
);
    inspection.setProcessGroupFlow(flow);
    inspection.setTime(System.currentTimeMillis() - start);
  }
  return inspection;
}
