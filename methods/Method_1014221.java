@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt16(scanInterval);
  serializeUInt16(scanWindow);
  serializeBoolean(activeScanning);
  return getPayload();
}
