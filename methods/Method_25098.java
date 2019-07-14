int calculateShadowWidth(){
  return mShowShadow ? (mShadowRadius + Math.abs(mShadowXOffset)) : 0;
}
