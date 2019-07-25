@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeBoolean(requireMitm);
  serializeUInt8(minKeySize);
  serializeSmpIoCapabilities(ioCapabilities);
  return getPayload();
}
