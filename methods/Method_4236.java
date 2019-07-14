@Override protected void onEnabled(boolean joining) throws ExoPlaybackException {
  super.onEnabled(joining);
  eventDispatcher.enabled(decoderCounters);
  int tunnelingAudioSessionId=getConfiguration().tunnelingAudioSessionId;
  if (tunnelingAudioSessionId != C.AUDIO_SESSION_ID_UNSET) {
    audioSink.enableTunnelingV21(tunnelingAudioSessionId);
  }
 else {
    audioSink.disableTunneling();
  }
}
