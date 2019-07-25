@TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override public int write(@NonNull ByteBuffer audioData,int sizeInBytes,int writeMode){
  byte[] trimmed=new byte[sizeInBytes];
  int position=audioData.position();
  audioData.get(trimmed,0,sizeInBytes);
  audioData.position(position);
  callback.onWebRtcAudioRecordSamplesReady(new AudioSamples(originalTrack.getAudioFormat(),originalTrack.getChannelCount(),originalTrack.getSampleRate(),trimmed));
  return originalTrack.write(audioData,sizeInBytes,writeMode);
}
