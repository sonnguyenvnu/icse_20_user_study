private boolean shouldUseFloatOutput(Format inputFormat){
  Assertions.checkNotNull(inputFormat.sampleMimeType);
  if (!enableFloatOutput || !supportsOutput(inputFormat.channelCount,C.ENCODING_PCM_FLOAT)) {
    return false;
  }
switch (inputFormat.sampleMimeType) {
case MimeTypes.AUDIO_RAW:
    return inputFormat.pcmEncoding == C.ENCODING_PCM_24BIT || inputFormat.pcmEncoding == C.ENCODING_PCM_32BIT || inputFormat.pcmEncoding == C.ENCODING_PCM_FLOAT;
case MimeTypes.AUDIO_AC3:
  return false;
default :
return true;
}
}
