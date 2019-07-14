public void initPresentMode(float x,float y,int stopColor){
  presentMode=true;
  showStopButton=stopColor != 0;
  stopButtonColor=stopColor;
  presentX=x;
  presentY=y;
  enableFBOLayer();
}
