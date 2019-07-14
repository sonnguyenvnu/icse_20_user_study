@Override public void release(boolean async){
  Log.i(TAG,"Release " + Integer.toHexString(System.identityHashCode(this)) + " [" + ExoPlayerLibraryInfo.VERSION_SLASHY + "] [" + Util.DEVICE_DEBUG_INFO + "] [" + ExoPlayerLibraryInfo.registeredModules() + "]");
  mediaSource=null;
  internalPlayer.release();
  eventHandler.removeCallbacksAndMessages(null);
}
