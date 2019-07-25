@Override public boolean test(String url){
  if (mPrefs.isAppJustInstalled() || (BuildConfig.DEBUG && mRunCount++ <= 1)) {
    mPrefs.setPlaybackWorking(SmartPreferences.PLAYBACK_UNKNOWN);
  }
  if (mWorkCount > 3 || mPrefs.getPlaybackWorking() == SmartPreferences.PLAYBACK_NOT_WORKING) {
    return false;
  }
  if (url.contains(PRESTART_VIDEO_URL)) {
    if (mState == SmartPreferences.PLAYBACK_NOT_WORKING) {
      mPrefs.setPlaybackWorking(SmartPreferences.PLAYBACK_NOT_WORKING);
      Log.d(TAG,"Playback is NOT working!!!");
    }
  }
 else   if (url.contains(START_VIDEO_URL)) {
    mState=SmartPreferences.PLAYBACK_NOT_WORKING;
    Log.d(TAG,"Playback is NOT working.. yet!!!");
  }
 else   if (url.contains(PLAYBACK_URL)) {
    mState=SmartPreferences.PLAYBACK_IS_WORKING;
    mWorkCount++;
    Log.d(TAG,"Playback is working!!!");
  }
  return false;
}
