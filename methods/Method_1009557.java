protected Collection<D> find(DeviceType deviceType,D current){
  Collection<D> devices=new HashSet<>();
  if (current.getType() != null && current.getType().implementsVersion(deviceType)) {
    devices.add(current);
  }
  if (current.hasEmbeddedDevices()) {
    for (    D embeddedDevice : (D[])current.getEmbeddedDevices()) {
      devices.addAll(find(deviceType,embeddedDevice));
    }
  }
  return devices;
}
