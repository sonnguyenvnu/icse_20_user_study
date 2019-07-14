@Override public void reset(){
  enabled=false;
  flush();
  buffer=EMPTY_BUFFER;
  channelCount=Format.NO_VALUE;
  sampleRateHz=Format.NO_VALUE;
  paddingSize=0;
  maybeSilenceBuffer=Util.EMPTY_BYTE_ARRAY;
  paddingBuffer=Util.EMPTY_BYTE_ARRAY;
}
