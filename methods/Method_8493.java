public void onResume(){
  paused=false;
  if (isShowing() && !isDismissed()) {
    checkCamera(false);
  }
}
