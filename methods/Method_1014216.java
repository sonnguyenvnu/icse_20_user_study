@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeGapDiscoverMode(mode);
  return getPayload();
}
