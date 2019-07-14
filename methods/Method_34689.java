private void unsubscribeAll(){
  healthCountsStream.unsubscribe();
  rollingCommandEventCounterStream.unsubscribe();
  cumulativeCommandEventCounterStream.unsubscribe();
  rollingCommandLatencyDistributionStream.unsubscribe();
  rollingCommandUserLatencyDistributionStream.unsubscribe();
  rollingCommandMaxConcurrencyStream.unsubscribe();
}
