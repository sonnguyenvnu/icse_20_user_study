@Override public void play(AudioStream audioStream,String sinkId,PercentType volume){
  AudioSink sink=getSink(sinkId);
  if (sink != null) {
    PercentType oldVolume=null;
    try {
      oldVolume=getVolume(sinkId);
    }
 catch (    IOException e) {
      logger.debug("An exception occurred while getting the volume of sink '{}' : {}",sink.getId(),e.getMessage(),e);
    }
    if (volume != null) {
      try {
        setVolume(volume,sinkId);
      }
 catch (      IOException e) {
        logger.debug("An exception occurred while setting the volume of sink '{}' : {}",sink.getId(),e.getMessage(),e);
      }
    }
    try {
      sink.process(audioStream);
    }
 catch (    UnsupportedAudioFormatException|UnsupportedAudioStreamException e) {
      logger.warn("Error playing '{}': {}",audioStream,e.getMessage(),e);
    }
 finally {
      if (volume != null && oldVolume != null) {
        try {
          setVolume(oldVolume,sinkId);
        }
 catch (        IOException e) {
          logger.debug("An exception occurred while setting the volume of sink '{}' : {}",sink.getId(),e.getMessage(),e);
        }
      }
    }
  }
 else {
    logger.warn("Failed playing audio stream '{}' as no audio sink was found.",audioStream);
  }
}
