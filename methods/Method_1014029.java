@Override public String serve(AudioStream stream){
  String streamId=UUID.randomUUID().toString();
  oneTimeStreams.put(streamId,stream);
  return getRelativeURL(streamId);
}
