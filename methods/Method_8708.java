public Rect getCameraRect(){
  cameraContainer.getLocationOnScreen(position);
  return new Rect(position[0],position[1],cameraContainer.getWidth(),cameraContainer.getHeight());
}
