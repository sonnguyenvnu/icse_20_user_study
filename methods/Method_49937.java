private void stopSelfIfIdle(int startId){
synchronized (mProcessing) {
    if (mProcessing.isEmpty() && mPending.isEmpty()) {
      Timber.v("stopSelfIfIdle: STOP!");
      stopSelf(startId);
    }
  }
}
