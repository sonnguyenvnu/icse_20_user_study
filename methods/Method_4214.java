private AudioTrack initializeKeepSessionIdAudioTrack(int audioSessionId){
  int sampleRate=4000;
  int channelConfig=AudioFormat.CHANNEL_OUT_MONO;
  @C.PcmEncoding int encoding=C.ENCODING_PCM_16BIT;
  int bufferSize=2;
  return new AudioTrack(C.STREAM_TYPE_DEFAULT,sampleRate,channelConfig,encoding,bufferSize,MODE_STATIC,audioSessionId);
}
