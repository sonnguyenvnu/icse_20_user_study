@Override public void consume(ParsableByteArray data){
  if (writingSample) {
    if (bytesToCheck == 2 && !checkNextByte(data,0x20)) {
      return;
    }
    if (bytesToCheck == 1 && !checkNextByte(data,0x00)) {
      return;
    }
    int dataPosition=data.getPosition();
    int bytesAvailable=data.bytesLeft();
    for (    TrackOutput output : outputs) {
      data.setPosition(dataPosition);
      output.sampleData(data,bytesAvailable);
    }
    sampleBytesWritten+=bytesAvailable;
  }
}
