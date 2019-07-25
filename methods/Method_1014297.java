@Override public Void parse(TclScriptDataList resultList) throws IOException {
  if (resultList.getEntries() != null) {
    Map<String,HmDevice> devicesByAddress=new HashMap<String,HmDevice>();
    for (    HmDevice device : devices) {
      devicesByAddress.put(device.getAddress(),device);
    }
    for (    TclScriptDataEntry entry : resultList.getEntries()) {
      HmDevice device=devicesByAddress.get(getSanitizedAddress(entry.name));
      if (device != null) {
        device.setName(entry.value);
      }
    }
  }
  return null;
}
