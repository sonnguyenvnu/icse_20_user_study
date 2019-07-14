@Override public void setUseSynchronousMode(boolean sync){
  if (!sync && looper == null) {
    sync=true;
    AsyncHttpClient.log.w(LOG_TAG,"Current thread has not called Looper.prepare(). Forcing synchronous mode.");
  }
  if (!sync && handler == null) {
    handler=new ResponderHandler(this,looper);
  }
 else   if (sync && handler != null) {
    handler=null;
  }
  useSynchronousMode=sync;
}
