public Map<String,Object> snapshot(){
  Map<String,Object> snap=Maps.newHashMap();
  snap.putAll(sessionStore);
  snap.putAll(newAttributes);
  for (  String name : deleteAttribute) {
    snap.remove(name);
  }
  return snap;
}
