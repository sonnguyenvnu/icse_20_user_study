/** 
 * Starts monitoring by continuously calling getEvent API.
 * @return true if it successfully started, false if a monitoring is alreadystarted.
 */
public boolean start(){
  if (!mIsActive) {
    Log.w(TAG,"start() observer is not active.");
    return false;
  }
  if (mWhileEventMonitoring) {
    Log.w(TAG,"start() already starting.");
    return false;
  }
  mWhileEventMonitoring=true;
  new Thread(){
    @Override public void run(){
      Log.d(TAG,"start() exec.");
      boolean firstCall=true;
      MONITORLOOP:       while (mWhileEventMonitoring) {
        boolean longPolling=!firstCall;
        try {
          JSONObject replyJson=mRemoteApi.getEvent(longPolling);
          int errorCode=findErrorCode(replyJson);
          Log.d(TAG,"getEvent errorCode: " + errorCode);
switch (errorCode) {
case 0:
            break;
case 1:
case 12:
          break MONITORLOOP;
case 2:
        continue MONITORLOOP;
case 40402:
      try {
        Thread.sleep(5000);
      }
 catch (      InterruptedException e) {
      }
    continue MONITORLOOP;
default :
  Log.w(TAG,"SimpleCameraEventObserver: Unexpected error: " + errorCode);
break MONITORLOOP;
}
List<String> availableApis=findAvailableApiList(replyJson);
if (!availableApis.isEmpty()) {
fireApiListModifiedListener(availableApis);
}
String cameraStatus=findCameraStatus(replyJson);
Log.d(TAG,"getEvent cameraStatus: " + cameraStatus);
if (cameraStatus != null && !cameraStatus.equals(mCameraStatus)) {
mCameraStatus=cameraStatus;
fireCameraStatusChangeListener(cameraStatus);
}
Boolean liveviewStatus=findLiveviewStatus(replyJson);
Log.d(TAG,"getEvent liveviewStatus: " + liveviewStatus);
if (liveviewStatus != null && !liveviewStatus.equals(mLiveviewStatus)) {
mLiveviewStatus=liveviewStatus;
fireLiveviewStatusChangeListener(liveviewStatus);
}
String shootMode=findShootMode(replyJson);
Log.d(TAG,"getEvent shootMode: " + shootMode);
if (shootMode != null && !shootMode.equals(mShootMode)) {
mShootMode=shootMode;
fireShootModeChangeListener(shootMode);
}
int zoomPosition=findZoomInformation(replyJson);
Log.d(TAG,"getEvent zoomPosition: " + zoomPosition);
if (zoomPosition != -1) {
mZoomPosition=zoomPosition;
fireZoomInformationChangeListener(0,0,zoomPosition,0);
}
String storageId=findStorageId(replyJson);
Log.d(TAG,"getEvent storageId:" + storageId);
if (storageId != null && !storageId.equals(mStorageId)) {
mStorageId=storageId;
fireStorageIdChangeListener(storageId);
}
String takePictureUrl=findTakePictureUrl(replyJson);
Log.d(TAG,"getEvent takePictureUrl:" + takePictureUrl);
if (takePictureUrl != null) {
firePictureTakenListener(takePictureUrl);
}
}
 catch (IOException e) {
Log.d(TAG,"getEvent timeout by client trigger.");
break MONITORLOOP;
}
catch (JSONException e) {
Log.w(TAG,"getEvent: JSON format error. " + e.getMessage());
break MONITORLOOP;
}
firstCall=false;
}
mWhileEventMonitoring=false;
}
}
.start();
return true;
}
