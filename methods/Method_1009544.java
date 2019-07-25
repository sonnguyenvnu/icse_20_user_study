public void execute(SubscriptionCallback callback){
  log.fine("Invoking subscription in background: " + callback);
  callback.setControlPoint(this);
  getConfiguration().getSyncProtocolExecutorService().execute(callback);
}
