@Override public boolean isHandlerThread(){
  return Thread.currentThread() == mHandler.getLooper().getThread();
}
