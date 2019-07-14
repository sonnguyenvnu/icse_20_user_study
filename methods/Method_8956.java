@Keep public void setY(int value){
  windowLayoutParams.y=value;
  windowManager.updateViewLayout(windowView,windowLayoutParams);
}
