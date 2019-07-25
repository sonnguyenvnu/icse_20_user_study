@Override public Title send(ProxiedPlayer player){
  sendPacket(player,clear);
  sendPacket(player,reset);
  sendPacket(player,times);
  sendPacket(player,subtitle);
  sendPacket(player,title);
  return this;
}
