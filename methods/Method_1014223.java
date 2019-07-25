@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt8(handle);
  serializeBoolean(bonding);
  return getPayload();
}
