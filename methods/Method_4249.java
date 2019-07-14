@Override public boolean configure(int sampleRateHz,int channelCount,@C.Encoding int encoding) throws UnhandledFormatException {
  if (encoding != C.ENCODING_PCM_8BIT && encoding != C.ENCODING_PCM_16BIT && encoding != C.ENCODING_PCM_24BIT && encoding != C.ENCODING_PCM_32BIT) {
    throw new UnhandledFormatException(sampleRateHz,channelCount,encoding);
  }
  if (this.sampleRateHz == sampleRateHz && this.channelCount == channelCount && this.encoding == encoding) {
    return false;
  }
  this.sampleRateHz=sampleRateHz;
  this.channelCount=channelCount;
  this.encoding=encoding;
  return true;
}
