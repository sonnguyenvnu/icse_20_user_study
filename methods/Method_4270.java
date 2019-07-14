@Override protected void onEnabled(boolean joining) throws ExoPlaybackException {
  decoderCounters=new DecoderCounters();
  eventDispatcher.enabled(decoderCounters);
  int tunnelingAudioSessionId=getConfiguration().tunnelingAudioSessionId;
  if (tunnelingAudioSessionId != C.AUDIO_SESSION_ID_UNSET) {
    audioSink.enableTunnelingV21(tunnelingAudioSessionId);
  }
 else {
    audioSink.disableTunneling();
  }
}
