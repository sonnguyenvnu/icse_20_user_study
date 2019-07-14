@Override public void setSize(int iwidth,int iheight){
  width=iwidth;
  height=iheight;
  updatePixelSize();
  texture=null;
  ptexture=null;
  defCameraFOV=60 * DEG_TO_RAD;
  defCameraX=width / 2.0f;
  defCameraY=height / 2.0f;
  defCameraZ=defCameraY / ((float)Math.tan(defCameraFOV / 2.0f));
  defCameraNear=defCameraZ / 10.0f;
  defCameraFar=defCameraZ * 10.0f;
  defCameraAspect=(float)width / (float)height;
  cameraFOV=defCameraFOV;
  cameraX=defCameraX;
  cameraY=defCameraY;
  cameraZ=defCameraZ;
  cameraNear=defCameraNear;
  cameraFar=defCameraFar;
  cameraAspect=defCameraAspect;
  sized=true;
}
