/** 
 * Acquire the MMS network
 * @throws MmsNetworkException if we fail to acquire it
 */
public Network acquireNetwork() throws MmsNetworkException {
synchronized (this) {
    mMmsRequestCount+=1;
    if (mNetwork != null) {
      Timber.d("MmsNetworkManager: already available");
      return mNetwork;
    }
    Timber.d("MmsNetworkManager: start new network request");
    newRequest();
    final long shouldEnd=SystemClock.elapsedRealtime() + NETWORK_ACQUIRE_TIMEOUT_MILLIS;
    long waitTime=NETWORK_ACQUIRE_TIMEOUT_MILLIS;
    while (waitTime > 0) {
      try {
        this.wait(waitTime);
      }
 catch (      InterruptedException e) {
        Timber.w("MmsNetworkManager: acquire network wait interrupted");
      }
      if (mNetwork != null || permissionError) {
        return mNetwork;
      }
      waitTime=shouldEnd - SystemClock.elapsedRealtime();
    }
    Timber.d("MmsNetworkManager: timed out");
    releaseRequestLocked(mNetworkCallback);
    throw new MmsNetworkException("Acquiring network timed out");
  }
}
