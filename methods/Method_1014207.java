@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt8(connection);
  serializeUInt8(attError);
  serializeUInt8Array(value);
  return getPayload();
}
