public Future execute(ActionCallback callback){
  log.fine("Invoking action in background: " + callback);
  callback.setControlPoint(this);
  ExecutorService executor=getConfiguration().getSyncProtocolExecutorService();
  return executor.submit(callback);
}
