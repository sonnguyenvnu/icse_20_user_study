@Override public void onScreenStateChanged(int screenState){
  super.onScreenStateChanged(screenState);
  if (screenState == View.SCREEN_STATE_OFF) {
    if (mSprite != null) {
      mSprite.stop();
    }
  }
}
