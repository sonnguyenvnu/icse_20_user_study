@Override public void run(){
  if (isCancelled()) {
    return;
  }
  if (!isRequestPreProcessed) {
    isRequestPreProcessed=true;
    onPreProcessRequest(this);
  }
  if (isCancelled()) {
    return;
  }
  responseHandler.sendStartMessage();
  if (isCancelled()) {
    return;
  }
  try {
    makeRequestWithRetries();
  }
 catch (  IOException e) {
    if (!isCancelled()) {
      responseHandler.sendFailureMessage(0,null,null,e);
    }
 else {
      AsyncHttpClient.log.e("AsyncHttpRequest","makeRequestWithRetries returned error",e);
    }
  }
  if (isCancelled()) {
    return;
  }
  responseHandler.sendFinishMessage();
  if (isCancelled()) {
    return;
  }
  onPostProcessRequest(this);
  isFinished=true;
}
