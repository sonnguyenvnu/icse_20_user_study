private boolean isOutputSupported(Format inputFormat){
  return shouldUseFloatOutput(inputFormat) || supportsOutput(inputFormat.channelCount,C.ENCODING_PCM_16BIT);
}
