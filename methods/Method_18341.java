public void unmount(){
  if (mDrawable != null) {
    setDrawableVisibilitySafe(false,false);
    mDrawable.setCallback(null);
  }
  mDrawable=null;
  mMatrix=null;
  mShouldClipRect=false;
  mWidth=mHeight=0;
}
