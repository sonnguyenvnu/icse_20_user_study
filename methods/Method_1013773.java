public ByteBuf output(){
  String pong;
  if (real != null) {
    pong=String.format("%s %s %s %d",PONG_PREFIX,direct.toString(),real.toString(),rtt);
  }
 else {
    pong=String.format("%s %s",PONG_PREFIX,direct.toString());
  }
  return new SimpleStringParser(pong).format();
}
