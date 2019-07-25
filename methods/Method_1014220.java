@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeGapDiscoverableMode(discover);
  serializeGapConnectableMode(connect);
  return getPayload();
}
