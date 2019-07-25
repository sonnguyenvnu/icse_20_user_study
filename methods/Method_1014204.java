@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt16(handle);
  serializeUInt16(offset);
  return getPayload();
}
