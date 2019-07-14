@Override public void onRepoWatched(boolean isWatched){
  watchRepoImage.tintDrawableColor(isWatched ? accentColor : iconColor);
  onEnableDisableWatch(true);
}
