@Override public int[] serialize(){
  serializeHeader(COMMAND_CLASS,COMMAND_METHOD);
  serializeBoolean(bondable);
  return getPayload();
}
