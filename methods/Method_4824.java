/** 
 * Returns the PCR value read from a given TS packet.
 * @param packetBuffer The buffer that holds the packet.
 * @param startOfPacket The starting position of the packet in the buffer.
 * @param pcrPid The PID for valid packets that contain PCR values.
 * @return The PCR value read from the packet, if its PID is equal to {@code pcrPid} and itcontains a valid PCR value. Returns  {@link C#TIME_UNSET} otherwise.
 */
public static long readPcrFromPacket(ParsableByteArray packetBuffer,int startOfPacket,int pcrPid){
  packetBuffer.setPosition(startOfPacket);
  if (packetBuffer.bytesLeft() < 5) {
    return C.TIME_UNSET;
  }
  int tsPacketHeader=packetBuffer.readInt();
  if ((tsPacketHeader & 0x800000) != 0) {
    return C.TIME_UNSET;
  }
  int pid=(tsPacketHeader & 0x1FFF00) >> 8;
  if (pid != pcrPid) {
    return C.TIME_UNSET;
  }
  boolean adaptationFieldExists=(tsPacketHeader & 0x20) != 0;
  if (!adaptationFieldExists) {
    return C.TIME_UNSET;
  }
  int adaptationFieldLength=packetBuffer.readUnsignedByte();
  if (adaptationFieldLength >= 7 && packetBuffer.bytesLeft() >= 7) {
    int flags=packetBuffer.readUnsignedByte();
    boolean pcrFlagSet=(flags & 0x10) == 0x10;
    if (pcrFlagSet) {
      byte[] pcrBytes=new byte[6];
      packetBuffer.readBytes(pcrBytes,0,pcrBytes.length);
      return readPcrValueFromPcrBytes(pcrBytes);
    }
  }
  return C.TIME_UNSET;
}
