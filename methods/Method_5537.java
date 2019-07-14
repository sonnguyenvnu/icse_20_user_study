private void handleSetPenLocation(){
  serviceBlockPacket.skipBits(4);
  int row=serviceBlockPacket.readBits(4);
  serviceBlockPacket.skipBits(2);
  int column=serviceBlockPacket.readBits(6);
  currentCueBuilder.setPenLocation(row,column);
}
