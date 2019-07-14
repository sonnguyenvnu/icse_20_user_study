@Override public void onRepoForked(boolean isForked){
  forkRepoImage.tintDrawableColor(isForked ? accentColor : iconColor);
  onEnableDisableFork(true);
}
