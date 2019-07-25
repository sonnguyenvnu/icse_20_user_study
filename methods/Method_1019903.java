public void run(){
  List<ToolInstance> toolInstanceList=toolInstanceDAO.hangInstances();
  for (  ToolInstance toolInstance : toolInstanceList) {
    try {
      List<ScanTool> scanToolList=scanToolDAO.getByInstanceId(toolInstance.getId());
      for (      ScanTool scanTool : scanToolList) {
        log.info("Pushing scans to queued for instance id and for scan id ...{}...{}",toolInstance.getId(),scanTool.getScanId());
        scanToolDAO.pushScanToQueued(toolInstance.getId(),Constants.SCAN_PROGRESS_STATUS,scanTool.getScanId());
        scanDAO.updateScanStatusToQueue(Constants.SCAN_QUEUED_STATUS,scanTool.getScanId());
      }
      Tool tool=toolDAO.get(toolInstance.getToolId());
      ToolManifest toolManifest=toolUtil.buildToolManifestRecord(tool);
      String appId=toolManifest.getId();
      Boolean enabledMarathon=Boolean.valueOf(System.getenv(Constants.ENABLED_MARATHON));
      if (enabledMarathon) {
        marathonClientManager.deleteApp(appId);
      }
 else {
        dockerUtil.stopContainers(toolManifest.getContainer().getDocker().getImage());
      }
    }
 catch (    MarathonException me) {
      log.error("MarathonException in tool while check tool instance hang state",me);
    }
catch (    Throwable th) {
      log.error("Throwable in tool while check tool instance hang state",th);
    }
  }
}
