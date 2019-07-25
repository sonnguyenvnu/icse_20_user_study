@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeUInt16(advIntervalMin);
  serializeUInt16(advIntervalMax);
  serializeUInt8(advChannels);
  return getPayload();
}
