private long readLastPcrValueFromBuffer(ParsableByteArray packetBuffer,int pcrPid){
  int searchStartPosition=packetBuffer.getPosition();
  int searchEndPosition=packetBuffer.limit();
  for (int searchPosition=searchEndPosition - 1; searchPosition >= searchStartPosition; searchPosition--) {
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
