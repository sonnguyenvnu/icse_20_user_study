@Keep public void setY(int value){
  windowLayoutParams.y=value;
  try {
    windowManager.updateViewLayout(windowView,windowLayoutParams);
  }
 catch (  Exception ignore) {
  }
}
