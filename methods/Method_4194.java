@Override public void configure(@C.Encoding int inputEncoding,int inputChannelCount,int inputSampleRate,int specifiedBufferSize,@Nullable int[] outputChannels,int trimStartFrames,int trimEndFrames) throws ConfigurationException {
  boolean flush=false;
  this.inputSampleRate=inputSampleRate;
  int channelCount=inputChannelCount;
  int sampleRate=inputSampleRate;
  isInputPcm=Util.isEncodingLinearPcm(inputEncoding);
  shouldConvertHighResIntPcmToFloat=enableConvertHighResIntPcmToFloat && supportsOutput(channelCount,C.ENCODING_PCM_32BIT) && Util.isEncodingHighResolutionIntegerPcm(inputEncoding);
  if (isInputPcm) {
    pcmFrameSize=Util.getPcmFrameSize(inputEncoding,channelCount);
  }
  @C.Encoding int encoding=inputEncoding;
  boolean processingEnabled=isInputPcm && inputEncoding != C.ENCODING_PCM_FLOAT;
  canApplyPlaybackParameters=processingEnabled && !shouldConvertHighResIntPcmToFloat;
  if (Util.SDK_INT < 21 && channelCount == 8 && outputChannels == null) {
    outputChannels=new int[6];
    for (int i=0; i < outputChannels.length; i++) {
      outputChannels[i]=i;
    }
  }
  if (processingEnabled) {
    trimmingAudioProcessor.setTrimFrameCount(trimStartFrames,trimEndFrames);
    channelMappingAudioProcessor.setChannelMap(outputChannels);
    for (    AudioProcessor audioProcessor : getAvailableAudioProcessors()) {
      try {
        flush|=audioProcessor.configure(sampleRate,channelCount,encoding);
      }
 catch (      AudioProcessor.UnhandledFormatException e) {
        throw new ConfigurationException(e);
      }
      if (audioProcessor.isActive()) {
        channelCount=audioProcessor.getOutputChannelCount();
        sampleRate=audioProcessor.getOutputSampleRateHz();
        encoding=audioProcessor.getOutputEncoding();
      }
    }
  }
  int channelConfig=getChannelConfig(channelCount,isInputPcm);
  if (channelConfig == AudioFormat.CHANNEL_INVALID) {
    throw new ConfigurationException("Unsupported channel count: " + channelCount);
  }
  if (!flush && isInitialized() && outputEncoding == encoding && outputSampleRate == sampleRate && outputChannelConfig == channelConfig) {
    return;
  }
  flush();
  this.processingEnabled=processingEnabled;
  outputSampleRate=sampleRate;
  outputChannelConfig=channelConfig;
  outputEncoding=encoding;
  outputPcmFrameSize=isInputPcm ? Util.getPcmFrameSize(outputEncoding,channelCount) : C.LENGTH_UNSET;
  bufferSize=specifiedBufferSize != 0 ? specifiedBufferSize : getDefaultBufferSize();
}
