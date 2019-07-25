@Override public void print(String msg){
  for (  String part : msg.split("\n")) {
    sender.sendMessage("\u00A7d" + part);
  }
}
