@Override public void print(String msg){
  for (  String part : msg.split("\n")) {
    player.sendMessage("\u00A7d" + part);
  }
}
