public static String getNodeKey(Client client){
  return client.getHost() + ":" + client.getPort();
}
