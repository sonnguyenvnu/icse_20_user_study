private synchronized void changeNumberOfTasks(int delta){
  Log.d(TAG,"changeNumberOfTasks:" + mNumTasks + ":" + delta);
  mNumTasks+=delta;
  if (mNumTasks <= 0) {
    Log.d(TAG,"stopping");
    stopSelf();
  }
}
