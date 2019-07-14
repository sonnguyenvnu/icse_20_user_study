private static int adjustMaxInputChannelCount(String name,String mimeType,int maxChannelCount){
  if (maxChannelCount > 1 || (Util.SDK_INT >= 26 && maxChannelCount > 0)) {
    return maxChannelCount;
  }
  if (MimeTypes.AUDIO_MPEG.equals(mimeType) || MimeTypes.AUDIO_AMR_NB.equals(mimeType) || MimeTypes.AUDIO_AMR_WB.equals(mimeType) || MimeTypes.AUDIO_AAC.equals(mimeType) || MimeTypes.AUDIO_VORBIS.equals(mimeType) || MimeTypes.AUDIO_OPUS.equals(mimeType) || MimeTypes.AUDIO_RAW.equals(mimeType) || MimeTypes.AUDIO_FLAC.equals(mimeType) || MimeTypes.AUDIO_ALAW.equals(mimeType) || MimeTypes.AUDIO_MLAW.equals(mimeType) || MimeTypes.AUDIO_MSGSM.equals(mimeType)) {
    return maxChannelCount;
  }
  int assumedMaxChannelCount;
  if (MimeTypes.AUDIO_AC3.equals(mimeType)) {
    assumedMaxChannelCount=6;
  }
 else   if (MimeTypes.AUDIO_E_AC3.equals(mimeType)) {
    assumedMaxChannelCount=16;
  }
 else {
    assumedMaxChannelCount=30;
  }
  Log.w(TAG,"AssumedMaxChannelAdjustment: " + name + ", [" + maxChannelCount + " to " + assumedMaxChannelCount + "]");
  return assumedMaxChannelCount;
}
