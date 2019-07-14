private void verifyApplicationThread(){
  if (Looper.myLooper() != getApplicationLooper()) {
    Log.w(TAG,"Player is accessed on the wrong thread. See " + "https://google.github.io/ExoPlayer/faqs.html#" + "what-do-player-is-accessed-on-the-wrong-thread-warnings-mean",hasNotifiedFullWrongThreadWarning ? null : new IllegalStateException());
    hasNotifiedFullWrongThreadWarning=true;
  }
}
