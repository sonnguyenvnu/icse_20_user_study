private void updateBrightness(float level){
  WindowManager.LayoutParams lp=getWindow().getAttributes();
  lp.screenBrightness=level;
  getWindow().setAttributes(lp);
}
