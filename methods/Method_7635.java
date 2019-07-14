public void setRotation(float rotation,boolean animated){
  lastFrameTime=0;
  if (currentRotation == 1) {
    reverseAngle=true;
  }
 else   if (currentRotation == 0) {
    reverseAngle=false;
  }
  lastFrameTime=0;
  if (animated) {
    if (currentRotation < rotation) {
      currentAnimationTime=(int)(currentRotation * animationTime);
    }
 else {
      currentAnimationTime=(int)((1.0f - currentRotation) * animationTime);
    }
    lastFrameTime=System.currentTimeMillis();
    finalRotation=rotation;
  }
 else {
    finalRotation=currentRotation=rotation;
  }
  invalidateSelf();
}
