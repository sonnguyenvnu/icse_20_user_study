@Override @SuppressWarnings("unchecked") public Void parse(Object[] message) throws IOException {
  Map<String,HmDevice> devicesById=new HashMap<String,HmDevice>();
  for (  HmDevice device : devices) {
    devicesById.put(device.getHomegearId(),device);
  }
  message=(Object[])message[0];
  for (int i=0; i < message.length; i++) {
    Map<String,?> data=(Map<String,?>)message[i];
    String id=toString(data.get("ID"));
    String name=toString(data.get("NAME"));
    HmDevice device=devicesById.get(getSanitizedAddress(id));
    if (device != null) {
      device.setName(name);
    }
  }
  return null;
}
