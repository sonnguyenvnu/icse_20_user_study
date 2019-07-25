@Override public ServerConfigBuilder development(boolean development){
  return addToServer(n -> n.put("development",development));
}
