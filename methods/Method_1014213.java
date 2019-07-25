@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt8(connection);
  serializeUInt16(intervalMin);
  serializeUInt16(intervalMax);
  serializeUInt16(latency);
  serializeUInt16(timeout);
  return getPayload();
}
