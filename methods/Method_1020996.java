@Override public void publish(Object message,String toChannel){
  String addr=getTopicAddr(toChannel);
  sendMsg(addr,message);
}
