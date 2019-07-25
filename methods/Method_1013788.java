@Override public void append(InetSocketAddress address){
  String ipAndPort=ChannelUtil.getSimpleIpport(address);
  if (originOptionString == null || originOptionString.isEmpty()) {
    originOptionString=option().name();
  }
  output=originOptionString + " " + ipAndPort;
}
