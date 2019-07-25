@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt8(setScanrsp);
  serializeUInt8Array(advData);
  return getPayload();
}
