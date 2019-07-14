public void step(){
  long time=System.currentTimeMillis();
  myAnimationPercent+=(time - myLastTime) / mDuration;
  if (myAnimationPercent > 1.0f) {
    myAnimationPercent=0;
  }
  myEasedPercent=(float)myEasing.get(myAnimationPercent);
  myCycleModel.setDot(myEasedPercent);
  call_count++;
  repaint();
  myLastTime=time;
}
