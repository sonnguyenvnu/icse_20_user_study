@TargetApi(21) public android.media.AudioAttributes getAudioAttributesV21(){
  if (audioAttributesV21 == null) {
    audioAttributesV21=new android.media.AudioAttributes.Builder().setContentType(contentType).setFlags(flags).setUsage(usage).build();
  }
  return audioAttributesV21;
}
