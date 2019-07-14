@Override public boolean configure(int sampleRateHz,int channelCount,@Encoding int encoding) throws UnhandledFormatException {
  boolean outputChannelsChanged=!Arrays.equals(pendingOutputChannels,outputChannels);
  outputChannels=pendingOutputChannels;
  if (outputChannels == null) {
    active=false;
    return outputChannelsChanged;
  }
  if (encoding != C.ENCODING_PCM_16BIT) {
    throw new UnhandledFormatException(sampleRateHz,channelCount,encoding);
  }
  if (!outputChannelsChanged && this.sampleRateHz == sampleRateHz && this.channelCount == channelCount) {
    return false;
  }
  this.sampleRateHz=sampleRateHz;
  this.channelCount=channelCount;
  active=channelCount != outputChannels.length;
  for (int i=0; i < outputChannels.length; i++) {
    int channelIndex=outputChannels[i];
    if (channelIndex >= channelCount) {
      throw new UnhandledFormatException(sampleRateHz,channelCount,encoding);
    }
    active|=(channelIndex != i);
  }
  return true;
}
