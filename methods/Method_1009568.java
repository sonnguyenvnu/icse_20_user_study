/** 
 * Returns root and embedded devices registered under the given UDN.
 * @param udn A unique device name.
 * @param rootOnly Set to true if only root devices (no embedded) should be searched
 * @return Any registered root or embedded device under the given UDN, <tt>null</tt> ifno device with the given UDN has been registered.
 */
D get(UDN udn,boolean rootOnly){
  for (  RegistryItem<UDN,D> item : deviceItems) {
    D device=item.getItem();
    if (device.getIdentity().getUdn().equals(udn)) {
      return device;
    }
    if (!rootOnly) {
      D foundDevice=(D)item.getItem().findDevice(udn);
      if (foundDevice != null)       return foundDevice;
    }
  }
  return null;
}
