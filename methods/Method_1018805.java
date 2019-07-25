private void reset(){
  if (null != mOnAlphaChangedListener) {
    mOnAlphaChangedListener.onTranslationYChanged(null,mTranslationY);
  }
}
