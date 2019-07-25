/** 
 * Returns the number of bytes that can be read (or skipped over) from this TDSReader without blocking by the next caller of a method for this TDSReader.
 * @return the actual number of bytes available.
 */
final int available(){
  int available=currentPacket.payloadLength - payloadOffset;
  for (TDSPacket packet=currentPacket.next; null != packet; packet=packet.next)   available+=packet.payloadLength;
  return available;
}
