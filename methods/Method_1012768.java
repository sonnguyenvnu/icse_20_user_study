public static void play(DLNAResource d,DeviceConfiguration r){
  DLNAResource d1=d.getParent() == null ? Temp.add(d) : d;
  if (d1 != null) {
    Device dev=getDevice(r.getUUID());
    String id=r.getInstanceID();
    setAVTransportURI(dev,id,d1.getURL(""),r.isPushMetadata() ? d1.getDidlString(r) : null);
    play(dev,id);
  }
}
