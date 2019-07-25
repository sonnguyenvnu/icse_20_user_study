protected D find(UDN udn,D current){
  if (current.getIdentity() != null && current.getIdentity().getUdn() != null) {
    if (current.getIdentity().getUdn().equals(udn))     return current;
  }
  if (current.hasEmbeddedDevices()) {
    for (    D embeddedDevice : (D[])current.getEmbeddedDevices()) {
      D match;
      if ((match=find(udn,embeddedDevice)) != null)       return match;
    }
  }
  return null;
}
