private void handleC3Command(int command){
  if (command <= 0x87) {
    serviceBlockPacket.skipBits(32);
  }
 else   if (command <= 0x8F) {
    serviceBlockPacket.skipBits(40);
  }
 else   if (command <= 0x9F) {
    serviceBlockPacket.skipBits(2);
    int length=serviceBlockPacket.readBits(6);
    serviceBlockPacket.skipBits(8 * length);
  }
}
