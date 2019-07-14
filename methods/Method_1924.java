public void updateBackendData(@Nullable AnimationBackend newBackend){
  if (mSeekBar == null) {
    return;
  }
  if (newBackend != null) {
    mSeekBar.setMax(newBackend.getFrameCount() - 1);
  }
 else {
    mSeekBar.setMax(0);
  }
}
