public void play(){
  if (myIsPlaying) {
    myCycleModel.setDot(Float.NaN);
    pause();
    myIsPlaying=false;
    return;
  }
  myPlayButton.setText("pause");
  myIsPlaying=true;
  myAttributeMask=myCycleModel.getAttributeMask();
  myPlayTimer.start();
}
