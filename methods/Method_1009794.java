public void prepare(RESCoreParameters coreParameters){
synchronized (syncOp) {
    workHandlerThread=new HandlerThread("RESRtmpSender,workHandlerThread");
    workHandlerThread.start();
    workHandler=new WorkHandler(coreParameters.senderQueueLength,new FLvMetaData(coreParameters),workHandlerThread.getLooper());
  }
}
