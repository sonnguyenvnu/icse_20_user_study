public boolean dismiss(View tipView,boolean byUser){
  if (tipView != null && isVisible(tipView)) {
    int key=(int)tipView.getTag();
    mTipsMap.remove(key);
    animateDismiss(tipView,byUser);
    return true;
  }
  return false;
}
