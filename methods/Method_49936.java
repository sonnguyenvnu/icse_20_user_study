public void onNewIntent(Intent intent,int serviceId){
  try {
    mobileDataEnabled=Utils.isMobileDataEnabled(this);
  }
 catch (  Exception e) {
    mobileDataEnabled=true;
  }
  mConnMgr=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
  if (!mobileDataEnabled) {
    Utils.setMobileDataEnabled(this,true);
  }
  if (mConnMgr == null) {
    endMmsConnectivity();
    stopSelf(serviceId);
    return;
  }
  boolean noNetwork=!isNetworkAvailable();
  Timber.v("onNewIntent: serviceId: " + serviceId + ": " + intent.getExtras() + " intent=" + intent);
  Timber.v("    networkAvailable=" + !noNetwork);
  String action=intent.getAction();
  if (ACTION_ONALARM.equals(action) || ACTION_ENABLE_AUTO_RETRIEVE.equals(action) || (intent.getExtras() == null)) {
    Cursor cursor=PduPersister.getPduPersister(this).getPendingMessages(System.currentTimeMillis());
    if (cursor != null) {
      try {
        int count=cursor.getCount();
        Timber.v("onNewIntent: cursor.count=" + count + " action=" + action);
        if (count == 0) {
          Timber.v("onNewIntent: no pending messages. Stopping service.");
          RetryScheduler.setRetryAlarm(this);
          stopSelfIfIdle(serviceId);
          return;
        }
        int columnIndexOfMsgId=cursor.getColumnIndexOrThrow(PendingMessages.MSG_ID);
        int columnIndexOfMsgType=cursor.getColumnIndexOrThrow(PendingMessages.MSG_TYPE);
        while (cursor.moveToNext()) {
          int msgType=cursor.getInt(columnIndexOfMsgType);
          int transactionType=getTransactionType(msgType);
          try {
            Uri uri=ContentUris.withAppendedId(Mms.CONTENT_URI,cursor.getLong(columnIndexOfMsgId));
            com.android.mms.transaction.DownloadManager.getInstance().downloadMultimediaMessage(this,PushReceiver.getContentLocation(this,uri),uri,false);
            break;
          }
 catch (          MmsException e) {
            e.printStackTrace();
          }
        }
      }
  finally {
        cursor.close();
      }
    }
 else {
      Timber.v("onNewIntent: no pending messages. Stopping service.");
      RetryScheduler.setRetryAlarm(this);
      stopSelfIfIdle(serviceId);
    }
  }
 else {
    Timber.v("onNewIntent: launch transaction...");
    TransactionBundle args=new TransactionBundle(intent.getExtras());
    launchTransaction(serviceId,args,noNetwork);
  }
}
