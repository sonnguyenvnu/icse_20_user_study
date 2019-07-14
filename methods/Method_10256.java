@Override public void onWindowFocusChanged(boolean hasFocus){
  if (hasFocus) {
    rope=YoYo.with(Techniques.FadeIn).duration(1000).playOn(mTarget);
  }
}
