private void init(Context ctx){
  mBounds=new Rect(0,0,0,0);
  mCurrentProgress=0;
  mTargetProgress=0;
  mHandler=new Handler(){
    @Override public void handleMessage(    Message msg){
      if (msg.what == MSG_UPDATE) {
        mCurrentProgress=Math.min(mTargetProgress,mCurrentProgress + mIncrement);
        mBounds.right=getWidth() * mCurrentProgress / MAX_PROGRESS;
        invalidate();
        if (mCurrentProgress < mTargetProgress) {
          sendMessageDelayed(mHandler.obtainMessage(MSG_UPDATE),DELAY);
        }
      }
    }
  }
;
}
