@SuppressWarnings("unchecked") @Override public GetDeviceDescriptionParser parse(Object[] message) throws IOException {
  if (message != null && message.length > 0 && message[0] instanceof Map) {
    Map<String,?> mapMessage=(Map<String,?>)message[0];
    type=toString(mapMessage.get("TYPE"));
    firmware=toString(mapMessage.get("FIRMWARE"));
    deviceInterface=toString(mapMessage.get("INTERFACE"));
  }
  return this;
}
