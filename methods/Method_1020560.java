public void reset(){
  Matrix.setIdentityM(mViewMatrix,0);
  Matrix.setIdentityM(mProjectionMatrix,0);
  Matrix.setIdentityM(mModelMatrix,0);
  Matrix.setIdentityM(viewProjectionMatrix,0);
  Matrix.setIdentityM(mvp,0);
  Matrix.setIdentityM(tempMultiplyMatrix4,0);
  Arrays.fill(transform,0);
  transform[SCALE_X]=1;
  transform[SCALE_Y]=1;
}
