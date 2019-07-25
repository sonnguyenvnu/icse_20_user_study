@Override public void receive(GossipData data){
switch (data.getType()) {
case MESSAGE:
    if (Objects.equals(local_addr,data.getSender()))     return;
  byte[] msg=data.getBuffer();
receive(data.getSender(),msg,0,msg.length);
break;
case SUSPECT:
Address suspect=data.getAddress();
if (suspect != null) {
log.debug("%s: firing suspect event for %s",local_addr,suspect);
up(new Event(Event.SUSPECT,Collections.singletonList(suspect)));
}
break;
}
}
