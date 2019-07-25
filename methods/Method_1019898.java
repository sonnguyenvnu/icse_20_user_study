public void run(){
  List<Scan> scanList=scanDAO.getScheduledScans();
  log.info("Total scheduled scans count ..{} {} ",scanList.size());
  for (  Scan scan : scanList) {
    if (canScan(scan)) {
      log.info("schedule scan picked with....{}..{}",scan.getId());
      scanUtil.startScan(scan);
    }
  }
  List<ScanTool> scanToolList=scanToolDAO.getAllProgressScans();
  for (  ScanTool scanTool : scanToolList) {
    Calendar calendar=Calendar.getInstance();
    calendar.add(Calendar.DATE,-1);
    java.sql.Date sqlDate=new java.sql.Date(calendar.getTime().getTime());
    if (sqlDate.compareTo(scanTool.getUpdatedAt()) >= 0) {
      scanToolDAO.setToolInstanceScanStatusToQueue(scanTool.getToolInstanceId(),Constants.SCAN_QUEUED_STATUS);
      ToolInstance toolInstance=toolInstanceDAO.get(scanTool.getToolInstanceId());
      if (toolInstance.getCurrentRunningScans() >= 1)       toolInstanceDAO.decreaseRunningScans(scanTool.getToolInstanceId());
      Scan scan=scanDAO.get(scanTool.getScanId());
      scan.setStatus(Constants.SCAN_QUEUED_STATUS);
      scanDAO.updateScanStatus(scan);
    }
  }
}
