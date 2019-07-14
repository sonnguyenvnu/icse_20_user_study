@Override protected void onDisabled(){
  flushPendingMetadata();
  decoder=null;
}
