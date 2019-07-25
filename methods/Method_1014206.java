@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt8(connection);
  serializeUInt16(handle);
  serializeUInt8Array(value);
  return getPayload();
}
