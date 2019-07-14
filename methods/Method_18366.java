private void registerHost(long id,ComponentHost host){
  host.suppressInvalidations(true);
  mHostsByMarker.put(id,host);
}
