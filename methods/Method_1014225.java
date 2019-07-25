@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt8(handle);
  serializeUInt32(passkey);
  return getPayload();
}
