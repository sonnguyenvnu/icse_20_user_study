public boolean isWasInBackground(boolean reset){
  if (reset && Build.VERSION.SDK_INT >= 21 && (System.currentTimeMillis() - enterBackgroundTime < 200)) {
    wasInBackground=false;
  }
  return wasInBackground;
}
