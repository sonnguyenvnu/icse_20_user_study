private void handleSetPenColor(){
  int foregroundO=serviceBlockPacket.readBits(2);
  int foregroundR=serviceBlockPacket.readBits(2);
  int foregroundG=serviceBlockPacket.readBits(2);
  int foregroundB=serviceBlockPacket.readBits(2);
  int foregroundColor=CueBuilder.getArgbColorFromCeaColor(foregroundR,foregroundG,foregroundB,foregroundO);
  int backgroundO=serviceBlockPacket.readBits(2);
  int backgroundR=serviceBlockPacket.readBits(2);
  int backgroundG=serviceBlockPacket.readBits(2);
  int backgroundB=serviceBlockPacket.readBits(2);
  int backgroundColor=CueBuilder.getArgbColorFromCeaColor(backgroundR,backgroundG,backgroundB,backgroundO);
  serviceBlockPacket.skipBits(2);
  int edgeR=serviceBlockPacket.readBits(2);
  int edgeG=serviceBlockPacket.readBits(2);
  int edgeB=serviceBlockPacket.readBits(2);
  int edgeColor=CueBuilder.getArgbColorFromCeaColor(edgeR,edgeG,edgeB);
  currentCueBuilder.setPenColor(foregroundColor,backgroundColor,edgeColor);
}
