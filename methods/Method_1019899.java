public void run(){
  ScanType scanType=scanTypeDAO.getWpScanType();
  List<Scan> scanList=scanDAO.getWordpressScans(scanType.getId());
  log.info("Total scheduled scans count ..{} {} ",scanList.size());
  for (  Scan scan : scanList) {
    scanUtil.startScan(scan);
  }
}
