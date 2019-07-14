@Override protected void onEnabled(boolean joining) throws ExoPlaybackException {
  super.onEnabled(joining);
  int oldTunnelingAudioSessionId=tunnelingAudioSessionId;
  tunnelingAudioSessionId=getConfiguration().tunnelingAudioSessionId;
  tunneling=tunnelingAudioSessionId != C.AUDIO_SESSION_ID_UNSET;
  if (tunnelingAudioSessionId != oldTunnelingAudioSessionId) {
    releaseCodec();
  }
  eventDispatcher.enabled(decoderCounters);
  frameReleaseTimeHelper.enable();
}
