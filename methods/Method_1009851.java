/** 
 * internal start.
 */
private void start(){
  Context applicationContext=getApplicationContext();
  mPushManager=Matrix.getInstance(applicationContext).getPushManager();
  mNotifiableEventResolver=new NotifiableEventResolver(applicationContext);
  StreamAction state=getServiceState();
  if (state == StreamAction.START) {
    Log.i(LOG_TAG,"start : Already started.");
    for (    MXSession session : mSessions) {
      session.refreshNetworkConnection();
    }
    return;
  }
 else   if ((state == StreamAction.PAUSE) || (state == StreamAction.CATCHUP)) {
    Log.i(LOG_TAG,"start : Resuming active stream.");
    resume();
    return;
  }
  if (mSessions == null) {
    Log.i(LOG_TAG,"start : No valid MXSession.");
    return;
  }
  Log.d(LOG_TAG,"## start : start the service");
  if (null != mActiveEventStreamService && this != mActiveEventStreamService) {
    mActiveEventStreamService.stop();
  }
  mActiveEventStreamService=this;
  for (  final MXSession session : mSessions) {
    if ((null == session)) {
      Log.i(LOG_TAG,"start : the session is not anymore valid.");
      return;
    }
    monitorSession(session);
  }
  refreshForegroundNotification();
  setServiceState(StreamAction.START);
}
