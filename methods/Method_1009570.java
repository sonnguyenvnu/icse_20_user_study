public void resume(){
  log.fine("Updating remote device expiration timestamps on resume");
  List<RemoteDeviceIdentity> toUpdate=new ArrayList<>();
  for (  RegistryItem<UDN,RemoteDevice> remoteItem : getDeviceItems()) {
    toUpdate.add(remoteItem.getItem().getIdentity());
  }
  for (  RemoteDeviceIdentity identity : toUpdate) {
    update(identity);
  }
}
