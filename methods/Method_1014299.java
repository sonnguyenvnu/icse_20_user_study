@Override public List<String> parse(Object[] message) throws IOException {
  List<String> adresses=new ArrayList<String>();
  if (message != null && message.length > 1) {
    Object[] data=(Object[])message[1];
    for (int i=0; i < message.length; i++) {
      String address=getSanitizedAddress(data[i]);
      boolean isDevice=!StringUtils.contains(address,":") && !StringUtils.startsWithIgnoreCase(address,"BidCos");
      if (isDevice) {
        adresses.add(address);
      }
    }
  }
  return adresses;
}
