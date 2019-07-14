@Override public void resetMatrix(){
  modelview.reset();
  modelviewInv.reset();
  projmodelview.set(projection);
  camera.reset();
  cameraInv.reset();
}
