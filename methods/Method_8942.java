public void onConfigurationChanged(){
  int sidex=preferences.getInt("sidex",1);
  int sidey=preferences.getInt("sidey",0);
  float px=preferences.getFloat("px",0);
  float py=preferences.getFloat("py",0);
  windowLayoutParams.x=getSideCoord(true,sidex,px,videoWidth);
  windowLayoutParams.y=getSideCoord(false,sidey,py,videoHeight);
  windowManager.updateViewLayout(windowView,windowLayoutParams);
}
