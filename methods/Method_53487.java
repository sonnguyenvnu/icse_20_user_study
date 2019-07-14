private synchronized void startHandler(TwitterStreamConsumer handler){
  cleanUp();
  this.handler=handler;
  this.handler.start();
  numberOfHandlers++;
}
