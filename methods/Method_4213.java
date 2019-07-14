@TargetApi(21) private AudioTrack createAudioTrackV21(){
  android.media.AudioAttributes attributes;
  if (tunneling) {
    attributes=new android.media.AudioAttributes.Builder().setContentType(android.media.AudioAttributes.CONTENT_TYPE_MOVIE).setFlags(android.media.AudioAttributes.FLAG_HW_AV_SYNC).setUsage(android.media.AudioAttributes.USAGE_MEDIA).build();
  }
 else {
    attributes=audioAttributes.getAudioAttributesV21();
  }
  AudioFormat format=new AudioFormat.Builder().setChannelMask(outputChannelConfig).setEncoding(outputEncoding).setSampleRate(outputSampleRate).build();
  int audioSessionId=this.audioSessionId != C.AUDIO_SESSION_ID_UNSET ? this.audioSessionId : AudioManager.AUDIO_SESSION_ID_GENERATE;
  return new AudioTrack(attributes,format,bufferSize,MODE_STREAM,audioSessionId);
}
