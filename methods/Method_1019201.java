private void init(HoodieWriteConfig config){
  this.multiPutBatchSize=config.getHbaseIndexGetBatchSize();
  this.qpsFraction=config.getHbaseIndexQPSFraction();
  this.maxQpsPerRegionServer=config.getHbaseIndexMaxQPSPerRegionServer();
  this.putBatchSizeCalculator=new HbasePutBatchSizeCalculator();
}
