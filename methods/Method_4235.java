@Override protected void onOutputFormatChanged(MediaCodec codec,MediaFormat outputFormat) throws ExoPlaybackException {
  @C.Encoding int encoding;
  MediaFormat format;
  if (passthroughMediaFormat != null) {
    encoding=MimeTypes.getEncoding(passthroughMediaFormat.getString(MediaFormat.KEY_MIME));
    format=passthroughMediaFormat;
  }
 else {
    encoding=pcmEncoding;
    format=outputFormat;
  }
  int channelCount=format.getInteger(MediaFormat.KEY_CHANNEL_COUNT);
  int sampleRate=format.getInteger(MediaFormat.KEY_SAMPLE_RATE);
  int[] channelMap;
  if (codecNeedsDiscardChannelsWorkaround && channelCount == 6 && this.channelCount < 6) {
    channelMap=new int[this.channelCount];
    for (int i=0; i < this.channelCount; i++) {
      channelMap[i]=i;
    }
  }
 else {
    channelMap=null;
  }
  try {
    audioSink.configure(encoding,channelCount,sampleRate,0,channelMap,encoderDelay,encoderPadding);
  }
 catch (  AudioSink.ConfigurationException e) {
    throw ExoPlaybackException.createForRenderer(e,getIndex());
  }
}
