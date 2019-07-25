@Override public void stream(String url,String sinkId) throws AudioException {
  AudioStream audioStream=url != null ? new URLAudioStream(url) : null;
  play(audioStream,sinkId,null);
}
