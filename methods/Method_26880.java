private void calculateOut(View sceneRoot,@NonNull Rect bounds,int[] outVector){
  sceneRoot.getLocationOnScreen(mTempLoc);
  int sceneRootX=mTempLoc[0];
  int sceneRootY=mTempLoc[1];
  int focalX;
  int focalY;
  Rect epicenter=getEpicenter();
  if (epicenter == null) {
    focalX=sceneRootX + (sceneRoot.getWidth() / 2) + Math.round(sceneRoot.getTranslationX());
    focalY=sceneRootY + (sceneRoot.getHeight() / 2) + Math.round(sceneRoot.getTranslationY());
  }
 else {
    focalX=epicenter.centerX();
    focalY=epicenter.centerY();
  }
  int centerX=bounds.centerX();
  int centerY=bounds.centerY();
  double xVector=centerX - focalX;
  double yVector=centerY - focalY;
  if (xVector == 0 && yVector == 0) {
    xVector=(Math.random() * 2) - 1;
    yVector=(Math.random() * 2) - 1;
  }
  double vectorSize=Math.hypot(xVector,yVector);
  xVector/=vectorSize;
  yVector/=vectorSize;
  double maxDistance=calculateMaxDistance(sceneRoot,focalX - sceneRootX,focalY - sceneRootY);
  outVector[0]=(int)Math.round(maxDistance * xVector);
  outVector[1]=(int)Math.round(maxDistance * yVector);
}
