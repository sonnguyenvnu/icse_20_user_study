/** 
 * @deprecated use {@link #setRotationTo(float)}
 */
@Override public void setPhotoViewRotation(float degrees){
  mSuppMatrix.setRotate(degrees % 360);
  checkAndDisplayMatrix();
}
