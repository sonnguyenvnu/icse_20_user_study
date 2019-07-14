private void handleSetPenAttributes(){
  int textTag=serviceBlockPacket.readBits(4);
  int offset=serviceBlockPacket.readBits(2);
  int penSize=serviceBlockPacket.readBits(2);
  boolean italicsToggle=serviceBlockPacket.readBit();
  boolean underlineToggle=serviceBlockPacket.readBit();
  int edgeType=serviceBlockPacket.readBits(3);
  int fontStyle=serviceBlockPacket.readBits(3);
  currentCueBuilder.setPenAttributes(textTag,offset,penSize,italicsToggle,underlineToggle,edgeType,fontStyle);
}
