/** 
 * @param packet packet
 * @param mtu mtu
 * @return a list containing fragmented packets.
 */
public static List<IpV4Packet> fragment(IpV4Packet packet,int mtu){
  List<IpV4Packet> list=new ArrayList<IpV4Packet>();
  if (packet.length() <= mtu) {
    list.add(packet);
    return list;
  }
  IpV4Header header=packet.getHeader();
  byte[] payload=packet.getPayload().getRawData();
  int max_payload_length=mtu - header.length();
  int actual_max_payload_length=max_payload_length % 8 == 0 ? max_payload_length : max_payload_length - max_payload_length % 8;
  int rest_length=payload.length;
  int srcPos=0;
  while (rest_length > 0) {
    if (rest_length > max_payload_length) {
      byte[] fragmented_payload=new byte[actual_max_payload_length];
      System.arraycopy(payload,srcPos,fragmented_payload,0,actual_max_payload_length);
      IpV4Packet.Builder b=packet.getBuilder();
      b.moreFragmentFlag(true).fragmentOffset((short)(srcPos / 8)).payloadBuilder(new UnknownPacket.Builder().rawData(fragmented_payload)).correctChecksumAtBuild(true).correctLengthAtBuild(true);
      list.add(b.build());
      rest_length-=fragmented_payload.length;
      srcPos+=fragmented_payload.length;
    }
 else {
      byte[] fragmented_payload=new byte[rest_length];
      System.arraycopy(payload,srcPos,fragmented_payload,0,rest_length);
      IpV4Packet.Builder b=packet.getBuilder();
      b.moreFragmentFlag(false).fragmentOffset((short)(srcPos / 8)).payloadBuilder(new UnknownPacket.Builder().rawData(fragmented_payload)).correctChecksumAtBuild(true).correctLengthAtBuild(true);
      list.add(b.build());
      break;
    }
  }
  return list;
}
