@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt16(handle);
  serializeUInt8(offset);
  serializeUInt8Array(value);
  return getPayload();
}
