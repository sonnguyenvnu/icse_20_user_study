private void updateAnimation(){
  long newTime=System.currentTimeMillis();
  long dt=newTime - lastUpdateTime;
  if (dt > 17) {
    dt=17;
  }
  lastUpdateTime=newTime;
  radOffset+=360 * dt / rotationTime;
  int count=(int)(radOffset / 360);
  radOffset-=count * 360;
  currentProgressTime+=dt;
  if (currentProgressTime >= risingTime) {
    currentProgressTime=risingTime;
  }
  if (risingCircleLength) {
    currentCircleLength=4 + 266 * accelerateInterpolator.getInterpolation(currentProgressTime / risingTime);
  }
 else {
    currentCircleLength=4 - 270 * (1.0f - decelerateInterpolator.getInterpolation(currentProgressTime / risingTime));
  }
  if (currentProgressTime == risingTime) {
    if (risingCircleLength) {
      radOffset+=270;
      currentCircleLength=-266;
    }
    risingCircleLength=!risingCircleLength;
    currentProgressTime=0;
  }
  invalidate();
}
