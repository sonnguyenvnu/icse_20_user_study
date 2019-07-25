void flush(boolean atEOM) throws SQLServerException {
  tdsChannel.write(socketBuffer.array(),((Buffer)socketBuffer).position(),socketBuffer.remaining());
  ((Buffer)socketBuffer).position(((Buffer)socketBuffer).limit());
  if (((Buffer)stagingBuffer).position() >= TDS_PACKET_HEADER_SIZE) {
    ByteBuffer swapBuffer=stagingBuffer;
    stagingBuffer=socketBuffer;
    socketBuffer=swapBuffer;
    ((Buffer)socketBuffer).flip();
    ((Buffer)stagingBuffer).clear();
    if (tdsChannel.isLoggingPackets()) {
      tdsChannel.logPacket(logBuffer.array(),0,((Buffer)socketBuffer).limit(),this.toString() + " sending packet (" + ((Buffer)socketBuffer).limit() + " bytes)");
    }
    if (!atEOM)     preparePacket();
    tdsChannel.write(socketBuffer.array(),((Buffer)socketBuffer).position(),socketBuffer.remaining());
    ((Buffer)socketBuffer).position(((Buffer)socketBuffer).limit());
  }
}
