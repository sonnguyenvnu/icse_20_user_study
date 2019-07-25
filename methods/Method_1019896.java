public void run(){
  List<Scan> scanList=scanDAO.getQueuedScans();
  log.info("Pending scans count ==>{} {} ",scanList.size());
  for (  Scan scan : scanList) {
    scanUtil.startScan(scan);
  }
}
