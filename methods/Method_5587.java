@Override protected void onDisabled(){
  streamFormat=null;
  clearOutput();
  releaseDecoder();
}
