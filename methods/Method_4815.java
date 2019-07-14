private long readFirstPcrValueFromBuffer(ParsableByteArray packetBuffer,int pcrPid){
  int searchStartPosition=packetBuffer.getPosition();
  int searchEndPosition=packetBuffer.limit();
  for (int searchPosition=searchStartPosition; searchPosition < searchEndPosition; searchPosition++) {
    if (packetBuffer.data[searchPosition] != TsExtractor.TS_SYNC_BYTE) {
      continue;
    }
    long pcrValue=TsUtil.readPcrFromPacket(packetBuffer,searchPosition,pcrPid);
    if (pcrValue != C.TIME_UNSET) {
      return pcrValue;
    }
  }
  return C.TIME_UNSET;
}
