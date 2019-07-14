private static int getChannelConfig(int channelCount,boolean isInputPcm){
  if (Util.SDK_INT <= 28 && !isInputPcm) {
    if (channelCount == 7) {
      channelCount=8;
    }
 else     if (channelCount == 3 || channelCount == 4 || channelCount == 5) {
      channelCount=6;
    }
  }
  if (Util.SDK_INT <= 26 && "fugu".equals(Util.DEVICE) && !isInputPcm && channelCount == 1) {
    channelCount=2;
  }
  return Util.getAudioTrackChannelConfig(channelCount);
}
