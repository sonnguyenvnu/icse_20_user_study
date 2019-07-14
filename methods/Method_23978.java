public boolean insideStopButton(float x,float y){
  if (!showStopButton)   return false;
  return stopButtonX < x && x < stopButtonX + stopButtonWidth && -(closeButtonY + stopButtonHeight) < y && y < -closeButtonY;
}
