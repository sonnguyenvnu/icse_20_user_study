@Keep public void setX(int value){
  windowLayoutParams.x=value;
  try {
    windowManager.updateViewLayout(windowView,windowLayoutParams);
  }
 catch (  Exception ignore) {
  }
}
