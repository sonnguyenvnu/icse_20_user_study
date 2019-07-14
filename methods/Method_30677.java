private void updateStatusBarAndWindowBackground(int offset){
  float fraction=Math.max(0,1 - (float)offset / getHeight());
  int alpha=(int)(fraction * 0xFF);
  int statusBarColor=ColorUtils.blendAlphaComponent(mStatusBarColor,alpha);
  StatusBarColorUtils.set(statusBarColor,AppUtils.getActivityFromContext(getContext()));
  mWindowBackground.setAlpha(alpha);
}
