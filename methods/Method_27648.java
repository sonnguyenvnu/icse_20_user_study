@Override public void onGistForked(boolean isForked){
  forkGist.tintDrawableColor(isForked ? accentColor : iconColor);
  forkGist.setEnabled(true);
}
