@Override protected void onPositionReset(long positionUs,boolean joining){
  flushPendingMetadata();
  inputStreamEnded=false;
}
