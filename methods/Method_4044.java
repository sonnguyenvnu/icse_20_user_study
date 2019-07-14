private void createOrientationHelpers(){
  mPrimaryOrientation=OrientationHelper.createOrientationHelper(this,mOrientation);
  mSecondaryOrientation=OrientationHelper.createOrientationHelper(this,1 - mOrientation);
}
