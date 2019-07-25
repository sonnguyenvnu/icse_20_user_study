@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt8(connection);
  serializeUInt16(attHandle);
  serializeUInt16(offset);
  serializeUInt8Array(data);
  return getPayload();
}
