@Override public void packetFinished(){
  if (writingSample) {
    for (    TrackOutput output : outputs) {
      output.sampleMetadata(sampleTimeUs,C.BUFFER_FLAG_KEY_FRAME,sampleBytesWritten,0,null);
    }
    writingSample=false;
  }
}
