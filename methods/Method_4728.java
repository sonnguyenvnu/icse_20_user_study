@Override public void seek(){
  state=STATE_FINDING_SYNC;
  bytesRead=0;
  lastByteWas0B=false;
}
