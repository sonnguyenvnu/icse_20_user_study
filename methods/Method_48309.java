private DataPuller addDataPuller(SliceQuery sq,StoreTransaction stx) throws BackendException {
  final BlockingQueue<SliceResult> queue=new LinkedBlockingQueue<>(QUEUE_SIZE);
  dataQueues.add(queue);
  DataPuller dp=new DataPuller(sq,queue,KCVSUtil.getKeys(store,sq,storeFeatures,MAX_KEY_LENGTH,stx),job.getKeyFilter());
  dp.start();
  return dp;
}
