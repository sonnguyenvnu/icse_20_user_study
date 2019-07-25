public void start(final String workerName){
  this.threadFactory.setWorkName(workerName);
  this.ringBuffer=this.disruptor.start();
}
