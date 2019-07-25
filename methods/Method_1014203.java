@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt8(connection);
  serializeUInt8Array(handles);
  return getPayload();
}
