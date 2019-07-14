@Override public void consume(ParsableByteArray data){
  if (!writingSample) {
    return;
  }
  int bytesAvailable=data.bytesLeft();
  if (sampleBytesRead < ID3_HEADER_SIZE) {
    int headerBytesAvailable=Math.min(bytesAvailable,ID3_HEADER_SIZE - sampleBytesRead);
    System.arraycopy(data.data,data.getPosition(),id3Header.data,sampleBytesRead,headerBytesAvailable);
    if (sampleBytesRead + headerBytesAvailable == ID3_HEADER_SIZE) {
      id3Header.setPosition(0);
      if ('I' != id3Header.readUnsignedByte() || 'D' != id3Header.readUnsignedByte() || '3' != id3Header.readUnsignedByte()) {
        Log.w(TAG,"Discarding invalid ID3 tag");
        writingSample=false;
        return;
      }
      id3Header.skipBytes(3);
      sampleSize=ID3_HEADER_SIZE + id3Header.readSynchSafeInt();
    }
  }
  int bytesToWrite=Math.min(bytesAvailable,sampleSize - sampleBytesRead);
  output.sampleData(data,bytesToWrite);
  sampleBytesRead+=bytesToWrite;
}
