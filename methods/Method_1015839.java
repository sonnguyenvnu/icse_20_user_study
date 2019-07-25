private String key(ProxiedPlayer player){
  InetSocketAddress host=player.getPendingConnection().getVirtualHost();
  return player.getName() + ";" + host.getHostString() + ":" + host.getPort();
}
