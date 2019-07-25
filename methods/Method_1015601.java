@Override public void members(List<PingData> mbrs){
  PhysicalAddress own_physical_addr=(PhysicalAddress)down(new Event(Event.GET_PHYSICAL_ADDRESS,local_addr));
  PingData data=new PingData(local_addr,false,org.jgroups.util.NameCache.get(local_addr),own_physical_addr);
  PingHeader hdr=new PingHeader(PingHeader.GET_MBRS_REQ).clusterName(cluster_name);
  Set<PhysicalAddress> physical_addrs=mbrs.stream().filter(ping_data -> ping_data != null && ping_data.getPhysicalAddr() != null).map(PingData::getPhysicalAddr).collect(Collectors.toSet());
  for (  PhysicalAddress physical_addr : physical_addrs) {
    if (own_physical_addr.equals(physical_addr))     continue;
    final Message msg=new Message(physical_addr).setFlag(Message.Flag.INTERNAL,Message.Flag.DONT_BUNDLE,Message.Flag.OOB).putHeader(this.id,hdr).setBuffer(marshal(data));
    log.trace("%s: sending discovery request to %s",local_addr,msg.getDest());
    down_prot.down(msg);
  }
}
