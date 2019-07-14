private void stopFrameCallback(){
  mChoreographerCompat.removeFrameCallback(mFrameCallback);
  mHasPostedFrameCallback=false;
}
