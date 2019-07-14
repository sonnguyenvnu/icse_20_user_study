/** 
 * Returns the position of the end of the first TS packet (exclusive) in the packet buffer. <p>This may be a position beyond the buffer limit if the packet has not been read fully into the buffer, or if no packet could be found within the buffer.
 */
private int findEndOfFirstTsPacketInBuffer() throws ParserException {
  int searchStart=tsPacketBuffer.getPosition();
  int limit=tsPacketBuffer.limit();
  int syncBytePosition=TsUtil.findSyncBytePosition(tsPacketBuffer.data,searchStart,limit);
  tsPacketBuffer.setPosition(syncBytePosition);
  int endOfPacket=syncBytePosition + TS_PACKET_SIZE;
  if (endOfPacket > limit) {
    bytesSinceLastSync+=syncBytePosition - searchStart;
    if (mode == MODE_HLS && bytesSinceLastSync > TS_PACKET_SIZE * 2) {
      throw new ParserException("Cannot find sync byte. Most likely not a Transport Stream.");
    }
  }
 else {
    bytesSinceLastSync=0;
  }
  return endOfPacket;
}
