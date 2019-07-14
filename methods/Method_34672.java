public int getBatchSizeMean(){
  return rollingCollapserBatchSizeDistributionStream.getLatestMean();
}
