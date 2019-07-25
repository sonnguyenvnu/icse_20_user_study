@Override public void process(AudioStream audioStream) throws UnsupportedAudioFormatException, UnsupportedAudioStreamException {
  if (audioStream == null) {
    logger.trace("Stop currently playing stream.");
    handler.stopPlaying(OnOffType.ON);
  }
 else   if (audioStream instanceof URLAudioStream) {
    URLAudioStream urlAudioStream=(URLAudioStream)audioStream;
    handler.playURI(new StringType(urlAudioStream.getURL()));
    try {
      audioStream.close();
    }
 catch (    IOException e) {
    }
  }
 else   if (audioStream instanceof FixedLengthAudioStream) {
    if (callbackUrl != null) {
      String relativeUrl=audioHTTPServer.serve((FixedLengthAudioStream)audioStream,10).toString();
      String url=callbackUrl + relativeUrl;
      AudioFormat format=audioStream.getFormat();
      if (!ThingHandlerHelper.isHandlerInitialized(handler)) {
        logger.warn("Sonos speaker '{}' is not initialized - status is {}",handler.getThing().getUID(),handler.getThing().getStatus());
      }
 else       if (AudioFormat.WAV.isCompatible(format)) {
        handler.playNotificationSoundURI(new StringType(url + AudioStreamUtils.EXTENSION_SEPARATOR + FileAudioStream.WAV_EXTENSION));
      }
 else       if (AudioFormat.MP3.isCompatible(format)) {
        handler.playNotificationSoundURI(new StringType(url + AudioStreamUtils.EXTENSION_SEPARATOR + FileAudioStream.MP3_EXTENSION));
      }
 else {
        throw new UnsupportedAudioFormatException("Sonos only supports MP3 or WAV.",format);
      }
    }
 else {
      logger.warn("We do not have any callback url, so Sonos cannot play the audio stream!");
    }
  }
 else {
    IOUtils.closeQuietly(audioStream);
    throw new UnsupportedAudioStreamException("Sonos can only handle FixedLengthAudioStreams and URLAudioStreams.",audioStream.getClass());
  }
}
