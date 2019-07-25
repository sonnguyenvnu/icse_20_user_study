@Override public Void parse(Object[] message) throws IOException {
  if (message != null && message.length > 0 && !(message[0] instanceof Map)) {
    dp.setValue(convertToType(dp,message[0]));
    adjustRssiValue(dp);
  }
  return null;
}
