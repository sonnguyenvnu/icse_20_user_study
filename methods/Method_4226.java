@Override public boolean configure(int sampleRateHz,int channelCount,@C.Encoding int encoding) throws UnhandledFormatException {
  if (!Util.isEncodingHighResolutionIntegerPcm(encoding)) {
    throw new UnhandledFormatException(sampleRateHz,channelCount,encoding);
  }
  if (this.sampleRateHz == sampleRateHz && this.channelCount == channelCount && sourceEncoding == encoding) {
    return false;
  }
  this.sampleRateHz=sampleRateHz;
  this.channelCount=channelCount;
  sourceEncoding=encoding;
  return true;
}
