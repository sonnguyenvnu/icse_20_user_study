private AudioTrack initializeAudioTrack() throws InitializationException {
  AudioTrack audioTrack;
  if (Util.SDK_INT >= 21) {
    audioTrack=createAudioTrackV21();
  }
 else {
    int streamType=Util.getStreamTypeForAudioUsage(audioAttributes.usage);
    if (audioSessionId == C.AUDIO_SESSION_ID_UNSET) {
      audioTrack=new AudioTrack(streamType,outputSampleRate,outputChannelConfig,outputEncoding,bufferSize,MODE_STREAM);
    }
 else {
      audioTrack=new AudioTrack(streamType,outputSampleRate,outputChannelConfig,outputEncoding,bufferSize,MODE_STREAM,audioSessionId);
    }
  }
  int state=audioTrack.getState();
  if (state != STATE_INITIALIZED) {
    try {
      audioTrack.release();
    }
 catch (    Exception e) {
    }
    throw new InitializationException(state,outputSampleRate,outputChannelConfig,bufferSize);
  }
  return audioTrack;
}
