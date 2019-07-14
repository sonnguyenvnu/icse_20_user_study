@Override protected void onDisabled(){
  inputFormat=null;
  if (sourceDrmSession != null || codecDrmSession != null) {
    onReset();
  }
 else {
    flushOrReleaseCodec();
  }
}
