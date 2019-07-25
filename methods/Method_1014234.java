@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeBoolean(bootInDfu);
  return getPayload();
}
