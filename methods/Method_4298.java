@Override public boolean configure(int sampleRateHz,int channelCount,@Encoding int encoding) throws UnhandledFormatException {
  if (encoding != C.ENCODING_PCM_16BIT) {
    throw new UnhandledFormatException(sampleRateHz,channelCount,encoding);
  }
  int outputSampleRateHz=pendingOutputSampleRateHz == SAMPLE_RATE_NO_CHANGE ? sampleRateHz : pendingOutputSampleRateHz;
  if (this.sampleRateHz == sampleRateHz && this.channelCount == channelCount && this.outputSampleRateHz == outputSampleRateHz) {
    return false;
  }
  this.sampleRateHz=sampleRateHz;
  this.channelCount=channelCount;
  this.outputSampleRateHz=outputSampleRateHz;
  sonic=null;
  return true;
}
