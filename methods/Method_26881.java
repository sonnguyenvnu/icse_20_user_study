private static double calculateMaxDistance(View sceneRoot,int focalX,int focalY){
  int maxX=Math.max(focalX,sceneRoot.getWidth() - focalX);
  int maxY=Math.max(focalY,sceneRoot.getHeight() - focalY);
  return Math.hypot(maxX,maxY);
}
