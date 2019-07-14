/** 
 * Find all messages to be sent or downloaded before certain time.
 */
public Cursor getPendingMessages(long dueTime){
  if (!checkReadSmsPermissions()) {
    Timber.w("No read sms permissions have been granted");
    return null;
  }
  Uri.Builder uriBuilder=PendingMessages.CONTENT_URI.buildUpon();
  uriBuilder.appendQueryParameter("protocol","mms");
  String selection=PendingMessages.ERROR_TYPE + " < ?" + " AND " + PendingMessages.DUE_TIME + " <= ?";
  String[] selectionArgs=new String[]{String.valueOf(MmsSms.ERR_TYPE_GENERIC_PERMANENT),String.valueOf(dueTime)};
  return SqliteWrapper.query(mContext,mContentResolver,uriBuilder.build(),null,selection,selectionArgs,PendingMessages.DUE_TIME);
}
