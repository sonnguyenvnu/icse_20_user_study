@Override public AudioStream synthesize(String text,Voice voice,AudioFormat requestedFormat) throws TTSException {
  if ((null == text) || text.isEmpty()) {
    throw new TTSException("The passed text is null or empty");
  }
  if (!this.voices.contains(voice)) {
    throw new TTSException("The passed voice is unsupported");
  }
  boolean isAudioFormatSupported=false;
  for (  AudioFormat currentAudioFormat : this.audioFormats) {
    if (currentAudioFormat.isCompatible(requestedFormat)) {
      isAudioFormatSupported=true;
      break;
    }
  }
  if (!isAudioFormatSupported) {
    throw new TTSException("The passed AudioFormat is unsupported");
  }
  try {
    return new MacTTSAudioStream(text,voice,requestedFormat);
  }
 catch (  AudioException e) {
    throw new TTSException(e);
  }
}
