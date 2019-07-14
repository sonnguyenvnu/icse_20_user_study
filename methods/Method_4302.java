@Override public boolean configure(int sampleRateHz,int channelCount,@C.Encoding int encoding) throws UnhandledFormatException {
  this.sampleRateHz=sampleRateHz;
  this.channelCount=channelCount;
  this.encoding=encoding;
  boolean wasActive=isActive;
  isActive=true;
  return !wasActive;
}
