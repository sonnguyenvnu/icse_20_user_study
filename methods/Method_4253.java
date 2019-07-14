@Override public boolean configure(int sampleRateHz,int channelCount,int encoding) throws UnhandledFormatException {
  if (encoding != C.ENCODING_PCM_16BIT) {
    throw new UnhandledFormatException(sampleRateHz,channelCount,encoding);
  }
  if (this.sampleRateHz == sampleRateHz && this.channelCount == channelCount) {
    return false;
  }
  this.sampleRateHz=sampleRateHz;
  this.channelCount=channelCount;
  bytesPerFrame=channelCount * 2;
  return true;
}
