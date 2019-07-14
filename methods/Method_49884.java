/** 
 * Release the MMS network when nobody is holding on to it.
 */
public void releaseNetwork(){
synchronized (this) {
    if (mMmsRequestCount > 0) {
      mMmsRequestCount-=1;
      Timber.d("MmsNetworkManager: release, count=" + mMmsRequestCount);
      if (mMmsRequestCount < 1) {
        releaseRequestLocked(mNetworkCallback);
      }
    }
  }
}
