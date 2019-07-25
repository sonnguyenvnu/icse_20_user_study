public static void main(String[] args) throws PcapNativeException, NotOpenException {
  String strSrcIpAddress="192.0.2.100";
  String strDstIpAddress=args[0];
  System.out.println(COUNT_KEY + ": " + COUNT);
  System.out.println(READ_TIMEOUT_KEY + ": " + READ_TIMEOUT);
  System.out.println(SNAPLEN_KEY + ": " + SNAPLEN);
  System.out.println("\n");
  PcapNetworkInterface nif;
  try {
    nif=new NifSelector().selectNetworkInterface();
  }
 catch (  IOException e) {
    e.printStackTrace();
    return;
  }
  if (nif == null) {
    return;
  }
  System.out.println(nif.getName() + "(" + nif.getDescription() + ")");
  PcapHandle handle=nif.openLive(SNAPLEN,PromiscuousMode.PROMISCUOUS,READ_TIMEOUT);
  PcapHandle sendHandle=nif.openLive(SNAPLEN,PromiscuousMode.PROMISCUOUS,READ_TIMEOUT);
  ExecutorService pool=Executors.newSingleThreadExecutor();
  try {
    handle.setFilter("arp and src host " + strDstIpAddress + " and dst host " + strSrcIpAddress + " and ether dst " + Pcaps.toBpfString(SRC_MAC_ADDR),BpfCompileMode.OPTIMIZE);
    PacketListener listener=new PacketListener(){
      @Override public void gotPacket(      Packet packet){
        if (packet.contains(ArpPacket.class)) {
          ArpPacket arp=packet.get(ArpPacket.class);
          if (arp.getHeader().getOperation().equals(ArpOperation.REPLY)) {
            SendArpRequest.resolvedAddr=arp.getHeader().getSrcHardwareAddr();
          }
        }
        System.out.println(packet);
      }
    }
;
    Task t=new Task(handle,listener);
    pool.execute(t);
    ArpPacket.Builder arpBuilder=new ArpPacket.Builder();
    try {
      arpBuilder.hardwareType(ArpHardwareType.ETHERNET).protocolType(EtherType.IPV4).hardwareAddrLength((byte)MacAddress.SIZE_IN_BYTES).protocolAddrLength((byte)ByteArrays.INET4_ADDRESS_SIZE_IN_BYTES).operation(ArpOperation.REQUEST).srcHardwareAddr(SRC_MAC_ADDR).srcProtocolAddr(InetAddress.getByName(strSrcIpAddress)).dstHardwareAddr(MacAddress.ETHER_BROADCAST_ADDRESS).dstProtocolAddr(InetAddress.getByName(strDstIpAddress));
    }
 catch (    UnknownHostException e) {
      throw new IllegalArgumentException(e);
    }
    EthernetPacket.Builder etherBuilder=new EthernetPacket.Builder();
    etherBuilder.dstAddr(MacAddress.ETHER_BROADCAST_ADDRESS).srcAddr(SRC_MAC_ADDR).type(EtherType.ARP).payloadBuilder(arpBuilder).paddingAtBuild(true);
    for (int i=0; i < COUNT; i++) {
      Packet p=etherBuilder.build();
      System.out.println(p);
      sendHandle.sendPacket(p);
      try {
        Thread.sleep(1000);
      }
 catch (      InterruptedException e) {
        break;
      }
    }
  }
  finally {
    if (handle != null && handle.isOpen()) {
      handle.close();
    }
    if (sendHandle != null && sendHandle.isOpen()) {
      sendHandle.close();
    }
    if (pool != null && !pool.isShutdown()) {
      pool.shutdown();
    }
    System.out.println(strDstIpAddress + " was resolved to " + resolvedAddr);
  }
}
