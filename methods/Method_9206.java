@Override public boolean onFragmentCreate(){
  super.onFragmentCreate();
  DownloadController.getInstance(currentAccount).loadAutoDownloadConfig(true);
  rowCount=0;
  usageSectionRow=rowCount++;
  storageUsageRow=rowCount++;
  dataUsageRow=rowCount++;
  usageSection2Row=rowCount++;
  mediaDownloadSectionRow=rowCount++;
  mobileRow=rowCount++;
  wifiRow=rowCount++;
  roamingRow=rowCount++;
  resetDownloadRow=rowCount++;
  mediaDownloadSection2Row=rowCount++;
  autoplayHeaderRow=rowCount++;
  autoplayGifsRow=rowCount++;
  autoplayVideoRow=rowCount++;
  autoplaySectionRow=rowCount++;
  streamSectionRow=rowCount++;
  enableStreamRow=rowCount++;
  if (BuildVars.DEBUG_VERSION) {
    enableMkvRow=rowCount++;
    enableAllStreamRow=rowCount++;
  }
 else {
    enableAllStreamRow=-1;
    enableMkvRow=-1;
  }
  enableAllStreamInfoRow=rowCount++;
  enableCacheStreamRow=-1;
  callsSectionRow=rowCount++;
  useLessDataForCallsRow=rowCount++;
  quickRepliesRow=rowCount++;
  callsSection2Row=rowCount++;
  proxySectionRow=rowCount++;
  proxyRow=rowCount++;
  proxySection2Row=rowCount++;
  return true;
}
