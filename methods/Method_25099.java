int calculateShadowHeight(){
  return mShowShadow ? (mShadowRadius + Math.abs(mShadowYOffset)) : 0;
}
