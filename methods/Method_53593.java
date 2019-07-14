private void applyConfig(){
  if (mShowBigImage) {
    mConstraintSetBig.applyTo(mRootLayout);
  }
 else {
    mConstraintSetNormal.applyTo(mRootLayout);
  }
}
