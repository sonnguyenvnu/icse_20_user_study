private boolean parseHeader(){
  pesScratch.setPosition(0);
  int startCodePrefix=pesScratch.readBits(24);
  if (startCodePrefix != 0x000001) {
    Log.w(TAG,"Unexpected start code prefix: " + startCodePrefix);
    payloadSize=-1;
    return false;
  }
  pesScratch.skipBits(8);
  int packetLength=pesScratch.readBits(16);
  pesScratch.skipBits(5);
  dataAlignmentIndicator=pesScratch.readBit();
  pesScratch.skipBits(2);
  ptsFlag=pesScratch.readBit();
  dtsFlag=pesScratch.readBit();
  pesScratch.skipBits(6);
  extendedHeaderLength=pesScratch.readBits(8);
  if (packetLength == 0) {
    payloadSize=-1;
  }
 else {
    payloadSize=packetLength + 6 - HEADER_SIZE - extendedHeaderLength;
  }
  return true;
}
