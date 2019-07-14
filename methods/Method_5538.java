private void handleSetWindowAttributes(){
  int fillO=serviceBlockPacket.readBits(2);
  int fillR=serviceBlockPacket.readBits(2);
  int fillG=serviceBlockPacket.readBits(2);
  int fillB=serviceBlockPacket.readBits(2);
  int fillColor=CueBuilder.getArgbColorFromCeaColor(fillR,fillG,fillB,fillO);
  int borderType=serviceBlockPacket.readBits(2);
  int borderR=serviceBlockPacket.readBits(2);
  int borderG=serviceBlockPacket.readBits(2);
  int borderB=serviceBlockPacket.readBits(2);
  int borderColor=CueBuilder.getArgbColorFromCeaColor(borderR,borderG,borderB);
  if (serviceBlockPacket.readBit()) {
    borderType|=0x04;
  }
  boolean wordWrapToggle=serviceBlockPacket.readBit();
  int printDirection=serviceBlockPacket.readBits(2);
  int scrollDirection=serviceBlockPacket.readBits(2);
  int justification=serviceBlockPacket.readBits(2);
  serviceBlockPacket.skipBits(8);
  currentCueBuilder.setWindowAttributes(fillColor,borderColor,wordWrapToggle,borderType,printDirection,scrollDirection,justification);
}
