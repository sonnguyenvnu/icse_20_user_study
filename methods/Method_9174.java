@Override public void onError(Exception e){
  FileLog.e(e);
  onInitFailed();
}
