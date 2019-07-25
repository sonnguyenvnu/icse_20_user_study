public void apply(){
  if (!getEnabled()) {
    Log.d(TAG,"apply: autoframerate not enabled... exiting...");
    return;
  }
  if (mPlayer == null || mPlayer.getVideoFormat() == null) {
    Log.e(TAG,"Can't apply mode change: player or format is null");
    return;
  }
  Format videoFormat=mPlayer.getVideoFormat();
  float frameRate=videoFormat.frameRate;
  int width=videoFormat.width;
  Log.d(TAG,String.format("Applying mode change... Video fps: %s, width: %s",frameRate,width));
  mSyncHelper.syncDisplayMode(mContext.getWindow(),width,frameRate);
}
