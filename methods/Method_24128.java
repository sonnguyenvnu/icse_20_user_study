@Override public void pushMatrix(){
  if (modelviewStackDepth == MATRIX_STACK_DEPTH) {
    throw new RuntimeException(ERROR_PUSHMATRIX_OVERFLOW);
  }
  modelview.get(modelviewStack[modelviewStackDepth]);
  modelviewInv.get(modelviewInvStack[modelviewStackDepth]);
  camera.get(cameraStack[modelviewStackDepth]);
  cameraInv.get(cameraInvStack[modelviewStackDepth]);
  modelviewStackDepth++;
}
