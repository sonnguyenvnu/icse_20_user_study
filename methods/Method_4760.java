private boolean checkNextByte(ParsableByteArray data,int expectedValue){
  if (data.bytesLeft() == 0) {
    return false;
  }
  if (data.readUnsignedByte() != expectedValue) {
    writingSample=false;
  }
  bytesToCheck--;
  return writingSample;
}
