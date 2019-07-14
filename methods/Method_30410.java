@Override public void release(){
  unregisterAudioBecomingNoisyReceiver();
  mPlayer.release();
}
