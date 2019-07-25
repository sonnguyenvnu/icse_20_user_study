@Override public void say(String text,String voiceId,String sinkId,PercentType volume){
  Objects.requireNonNull(text,"Text cannot be said as it is null.");
  try {
    TTSService tts=null;
    Voice voice=null;
    String selectedVoiceId=voiceId;
    if (selectedVoiceId == null) {
      selectedVoiceId=defaultVoice;
    }
    if (selectedVoiceId == null) {
      tts=getTTS();
      if (tts != null) {
        voice=getPreferredVoice(tts.getAvailableVoices());
      }
    }
 else {
      voice=getVoice(selectedVoiceId);
      if (voice != null) {
        tts=getTTS(voice);
      }
    }
    if (tts == null) {
      throw new TTSException("No TTS service can be found for voice " + selectedVoiceId);
    }
    if (voice == null) {
      throw new TTSException("Unable to find a voice for language " + localeProvider.getLocale().getLanguage());
    }
    Set<AudioFormat> audioFormats=tts.getSupportedFormats();
    AudioSink sink=audioManager.getSink(sinkId);
    if (sink != null) {
      AudioFormat audioFormat=getBestMatch(audioFormats,sink.getSupportedFormats());
      if (audioFormat != null) {
        AudioStream audioStream=tts.synthesize(text,voice,audioFormat);
        if (sink.getSupportedStreams().stream().anyMatch(clazz -> clazz.isInstance(audioStream))) {
          PercentType oldVolume=null;
          try {
            oldVolume=audioManager.getVolume(sinkId);
          }
 catch (          IOException e) {
            logger.debug("An exception occurred while getting the volume of sink '{}' : {}",sink.getId(),e.getMessage(),e);
          }
          if (volume != null) {
            try {
              audioManager.setVolume(volume,sinkId);
            }
 catch (            IOException e) {
              logger.debug("An exception occurred while setting the volume of sink '{}' : {}",sink.getId(),e.getMessage(),e);
            }
          }
          try {
            sink.process(audioStream);
          }
 catch (          UnsupportedAudioFormatException|UnsupportedAudioStreamException e) {
            logger.warn("Error saying '{}': {}",text,e.getMessage(),e);
          }
 finally {
            if (volume != null && oldVolume != null) {
              try {
                audioManager.setVolume(oldVolume,sinkId);
              }
 catch (              IOException e) {
                logger.debug("An exception occurred while setting the volume of sink '{}' : {}",sink.getId(),e.getMessage(),e);
              }
            }
          }
        }
 else {
          logger.warn("Failed playing audio stream '{}' as audio sink doesn't support it.",audioStream);
        }
      }
 else {
        logger.warn("No compatible audio format found for TTS '{}' and sink '{}'",tts.getId(),sink.getId());
      }
    }
  }
 catch (  TTSException e) {
    logger.warn("Error saying '{}': {}",text,e.getMessage(),e);
  }
}
