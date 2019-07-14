@Override public void packetFinished(){
  if (!writingSample || sampleSize == 0 || sampleBytesRead != sampleSize) {
    return;
  }
  output.sampleMetadata(sampleTimeUs,C.BUFFER_FLAG_KEY_FRAME,sampleSize,0,null);
  writingSample=false;
}
