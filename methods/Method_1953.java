public void onSuccess(){
  Preconditions.checkState(mState == ImageRequestState.STARTED);
  mState=ImageRequestState.SUCCESS;
  mFinishTime=System.currentTimeMillis();
  final long elapsedTime=mFinishTime - mStartTime;
  mPerfListener.reportSuccess(elapsedTime);
  FLog.i(TAG,"Image [%s]: loaded after %d ms",mTag,elapsedTime);
}
