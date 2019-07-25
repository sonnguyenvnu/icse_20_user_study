@Override public String info(){
  String info="";
  long lag=System.currentTimeMillis() - replAckTime;
  info=String.format("ip=%s,port=%d,state=%s,offset=%d,lag=%d,remotePort=%d",getClientIpAddress() == null ? ip() : getClientIpAddress(),getSlaveListeningPort(),slaveState != null ? slaveState.getDesc() : "null",replAckOff,lag / 1000,remotePort());
  return info;
}
