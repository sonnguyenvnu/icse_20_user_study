@Override public void endTracks(){
  tracksEnded=true;
  handler.post(onTracksEndedRunnable);
}
