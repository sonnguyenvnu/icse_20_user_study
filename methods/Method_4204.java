@Override public void enableTunnelingV21(int tunnelingAudioSessionId){
  Assertions.checkState(Util.SDK_INT >= 21);
  if (!tunneling || audioSessionId != tunnelingAudioSessionId) {
    tunneling=true;
    audioSessionId=tunnelingAudioSessionId;
    flush();
  }
}
