public void toggleMode(View v){
  TransitionManager.beginDelayedTransition(mRootLayout);
  mShowBigImage=!mShowBigImage;
  applyConfig();
}
