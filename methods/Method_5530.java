private void handleC2Command(int command){
  if (command <= 0x07) {
  }
 else   if (command <= 0x0F) {
    serviceBlockPacket.skipBits(8);
  }
 else   if (command <= 0x17) {
    serviceBlockPacket.skipBits(16);
  }
 else   if (command <= 0x1F) {
    serviceBlockPacket.skipBits(24);
  }
}
