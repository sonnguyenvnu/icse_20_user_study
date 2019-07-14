@Override public void seek(){
  state=STATE_FINDING_HEADER;
  frameBytesRead=0;
  lastByteWasFF=false;
}
