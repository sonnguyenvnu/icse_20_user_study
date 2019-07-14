@Override public void popMatrix(){
  if (modelviewStackDepth == 0) {
    throw new RuntimeException(ERROR_PUSHMATRIX_UNDERFLOW);
  }
  modelviewStackDepth--;
  modelview.set(modelviewStack[modelviewStackDepth]);
  modelviewInv.set(modelviewInvStack[modelviewStackDepth]);
  camera.set(cameraStack[modelviewStackDepth]);
  cameraInv.set(cameraInvStack[modelviewStackDepth]);
  updateProjmodelview();
}
