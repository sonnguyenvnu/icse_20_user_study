@Override public void receive(Address sender,byte[] buf,int offset,int length){
  ByteArrayDataInputStream in=new ByteArrayDataInputStream(buf,offset,length);
  GossipData data=new GossipData();
  try {
    data.readFrom(in);
switch (data.getType()) {
case MESSAGE:
case SUSPECT:
      if (receiver != null)       receiver.receive(data);
    break;
case GET_MBRS_RSP:
  notifyResponse(data.getGroup(),data.getPingData());
break;
}
}
 catch (Exception ex) {
log.error(Util.getMessage("FailedReadingData"),ex);
}
}
