protected void init(){
  if (null == mAttacher || null == mAttacher.getImageView()) {
    mAttacher=new PhotoViewAttacher(this);
  }
  if (null != mPendingScaleType) {
    setScaleType(mPendingScaleType);
    mPendingScaleType=null;
  }
}
