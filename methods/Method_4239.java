@Override protected void onReset(){
  try {
    super.onReset();
  }
  finally {
    audioSink.reset();
  }
}
