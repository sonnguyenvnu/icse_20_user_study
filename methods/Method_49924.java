private void scheduleRetry(Uri uri){
  long msgId=ContentUris.parseId(uri);
  Uri.Builder uriBuilder=PendingMessages.CONTENT_URI.buildUpon();
  uriBuilder.appendQueryParameter("protocol","mms");
  uriBuilder.appendQueryParameter("message",String.valueOf(msgId));
  Cursor cursor=SqliteWrapper.query(mContext,mContentResolver,uriBuilder.build(),null,null,null,null);
  if (cursor != null) {
    try {
      if ((cursor.getCount() == 1) && cursor.moveToFirst()) {
        int msgType=cursor.getInt(cursor.getColumnIndexOrThrow(PendingMessages.MSG_TYPE));
        int retryIndex=cursor.getInt(cursor.getColumnIndexOrThrow(PendingMessages.RETRY_INDEX)) + 1;
        int errorType=MmsSms.ERR_TYPE_GENERIC;
        DefaultRetryScheme scheme=new DefaultRetryScheme(mContext,retryIndex);
        ContentValues values=new ContentValues(4);
        long current=System.currentTimeMillis();
        boolean isRetryDownloading=(msgType == PduHeaders.MESSAGE_TYPE_NOTIFICATION_IND);
        boolean retry=true;
        int respStatus=getResponseStatus(msgId);
        int errorString=0;
        if (!isRetryDownloading) {
switch (respStatus) {
case PduHeaders.RESPONSE_STATUS_ERROR_SENDING_ADDRESS_UNRESOLVED:
            errorString=R.string.invalid_destination;
          break;
case PduHeaders.RESPONSE_STATUS_ERROR_SERVICE_DENIED:
case PduHeaders.RESPONSE_STATUS_ERROR_PERMANENT_SERVICE_DENIED:
        errorString=R.string.service_not_activated;
      break;
case PduHeaders.RESPONSE_STATUS_ERROR_NETWORK_PROBLEM:
    errorString=R.string.service_network_problem;
  break;
case PduHeaders.RESPONSE_STATUS_ERROR_TRANSIENT_MESSAGE_NOT_FOUND:
case PduHeaders.RESPONSE_STATUS_ERROR_PERMANENT_MESSAGE_NOT_FOUND:
errorString=R.string.service_message_not_found;
break;
}
if (errorString != 0) {
DownloadManager.init(mContext.getApplicationContext());
DownloadManager.getInstance().showErrorCodeToast(errorString);
retry=false;
}
}
 else {
respStatus=getRetrieveStatus(msgId);
if (respStatus == PduHeaders.RESPONSE_STATUS_ERROR_PERMANENT_MESSAGE_NOT_FOUND) {
DownloadManager.init(mContext.getApplicationContext());
DownloadManager.getInstance().showErrorCodeToast(R.string.service_message_not_found);
SqliteWrapper.delete(mContext,mContext.getContentResolver(),uri,null,null);
retry=false;
return;
}
}
if ((retryIndex < scheme.getRetryLimit()) && retry) {
long retryAt=current + scheme.getWaitingInterval();
Timber.v("scheduleRetry: retry for " + uri + " is scheduled at " + (retryAt - System.currentTimeMillis()) + "ms from now");
values.put(PendingMessages.DUE_TIME,retryAt);
if (isRetryDownloading) {
DownloadManager.init(mContext.getApplicationContext());
DownloadManager.getInstance().markState(uri,DownloadManager.STATE_TRANSIENT_FAILURE);
}
}
 else {
errorType=MmsSms.ERR_TYPE_GENERIC_PERMANENT;
if (isRetryDownloading) {
Cursor c=SqliteWrapper.query(mContext,mContext.getContentResolver(),uri,new String[]{Mms.THREAD_ID},null,null,null);
long threadId=-1;
if (c != null) {
try {
if (c.moveToFirst()) {
  threadId=c.getLong(0);
}
}
  finally {
c.close();
}
}
if (threadId != -1) {
markMmsFailed(mContext);
}
DownloadManager.init(mContext.getApplicationContext());
DownloadManager.getInstance().markState(uri,DownloadManager.STATE_PERMANENT_FAILURE);
}
 else {
ContentValues readValues=new ContentValues(1);
readValues.put(Mms.READ,0);
SqliteWrapper.update(mContext,mContext.getContentResolver(),uri,readValues,null,null);
markMmsFailed(mContext);
}
}
values.put(PendingMessages.ERROR_TYPE,errorType);
values.put(PendingMessages.RETRY_INDEX,retryIndex);
values.put(PendingMessages.LAST_TRY,current);
int columnIndex=cursor.getColumnIndexOrThrow(PendingMessages._ID);
long id=cursor.getLong(columnIndex);
SqliteWrapper.update(mContext,mContentResolver,PendingMessages.CONTENT_URI,values,PendingMessages._ID + "=" + id,null);
}
 else if (LOCAL_LOGV) {
Timber.v("Cannot found correct pending status for: " + msgId);
}
}
  finally {
cursor.close();
}
}
}
