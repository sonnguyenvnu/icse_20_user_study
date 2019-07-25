/** 
 * @return
 */
private ResultPoint transform(ResultPoint originPoint){
  Point screenPoint=cameraManager.getScreenResolution();
  Point cameraPoint=cameraManager.getCameraResolution();
  float scaleX;
  float scaleY;
  float x;
  float y;
  if (screenPoint.x < screenPoint.y) {
    scaleX=1.0f * screenPoint.x / cameraPoint.y;
    scaleY=1.0f * screenPoint.y / cameraPoint.x;
    x=originPoint.getX() * scaleX - Math.max(screenPoint.x,cameraPoint.y) / 2;
    y=originPoint.getY() * scaleY - Math.min(screenPoint.y,cameraPoint.x) / 2;
  }
 else {
    scaleX=1.0f * screenPoint.x / cameraPoint.x;
    scaleY=1.0f * screenPoint.y / cameraPoint.y;
    x=originPoint.getX() * scaleX - Math.min(screenPoint.y,cameraPoint.y) / 2;
    y=originPoint.getY() * scaleY - Math.max(screenPoint.x,cameraPoint.x) / 2;
  }
  return new ResultPoint(x,y);
}
