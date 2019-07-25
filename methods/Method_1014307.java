@Override @SuppressWarnings("unchecked") public List<String> parse(Object[] message) throws IOException {
  List<String> adresses=new ArrayList<String>();
  if (message != null && message.length > 1) {
    message=(Object[])message[1];
    for (int i=0; i < message.length; i++) {
      Map<String,?> data=(Map<String,?>)message[i];
      String address=toString(data.get("ADDRESS"));
      boolean isDevice=!StringUtils.contains(address,":") && !StringUtils.startsWithIgnoreCase(address,"BidCos");
      if (isDevice) {
        adresses.add(getSanitizedAddress(address));
      }
    }
  }
  return adresses;
}
