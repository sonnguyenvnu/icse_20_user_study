@Override public void process(AudioStream audioStream) throws UnsupportedAudioFormatException, UnsupportedAudioStreamException {
  if (audioStream == null) {
    logger.debug("Web Audio sink does not support stopping the currently playing stream.");
    return;
  }
  logger.debug("Received audio stream of format {}",audioStream.getFormat());
  if (audioStream instanceof URLAudioStream) {
    URLAudioStream urlAudioStream=(URLAudioStream)audioStream;
    sendEvent(urlAudioStream.getURL());
    IOUtils.closeQuietly(audioStream);
  }
 else   if (audioStream instanceof FixedLengthAudioStream) {
    sendEvent(audioHTTPServer.serve((FixedLengthAudioStream)audioStream,10).toString());
  }
 else {
    IOUtils.closeQuietly(audioStream);
    throw new UnsupportedAudioStreamException("Web audio sink can only handle FixedLengthAudioStreams and URLAudioStreams.",audioStream.getClass());
  }
}
