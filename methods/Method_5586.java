@Override protected void onPositionReset(long positionUs,boolean joining){
  clearOutput();
  inputStreamEnded=false;
  outputStreamEnded=false;
  if (decoderReplacementState != REPLACEMENT_STATE_NONE) {
    replaceDecoder();
  }
 else {
    releaseBuffers();
    decoder.flush();
  }
}
