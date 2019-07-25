@Override public void enqueue(Object message,String toChannel){
  String addr=getQueueAddr(toChannel);
  sendMsg(addr,message);
}
