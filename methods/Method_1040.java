public synchronized void drawFrame(int movieTime,int w,int h,Bitmap bitmap){
  mMovie.setTime(movieTime);
  if (mPreviousBitmap != null && mPreviousBitmap.isRecycled()) {
    mPreviousBitmap=null;
  }
  if (mPreviousBitmap != bitmap) {
    mPreviousBitmap=bitmap;
    mCanvas.setBitmap(bitmap);
  }
  mScaleHolder.updateViewPort(w,h);
  mCanvas.save();
  mCanvas.scale(mScaleHolder.getScale(),mScaleHolder.getScale());
  mMovie.draw(mCanvas,mScaleHolder.getLeft(),mScaleHolder.getTop());
  mCanvas.restore();
}
