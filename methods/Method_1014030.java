@Override public String serve(FixedLengthAudioStream stream,int seconds){
  String streamId=UUID.randomUUID().toString();
  multiTimeStreams.put(streamId,stream);
  streamTimeouts.put(streamId,System.nanoTime() + TimeUnit.SECONDS.toNanos(seconds));
  return getRelativeURL(streamId);
}
