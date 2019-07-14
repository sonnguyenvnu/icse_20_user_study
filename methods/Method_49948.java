public void markState(final Uri uri,int state){
  try {
    NotificationInd nInd=(NotificationInd)PduPersister.getPduPersister(mContext).load(uri);
    if ((nInd.getExpiry() < System.currentTimeMillis() / 1000L) && (state == STATE_DOWNLOADING || state == STATE_PRE_DOWNLOADING)) {
      mHandler.post(new Runnable(){
        public void run(){
          Toast.makeText(mContext,R.string.service_message_not_found,Toast.LENGTH_LONG).show();
        }
      }
);
      SqliteWrapper.delete(mContext,mContext.getContentResolver(),uri,null,null);
      return;
    }
  }
 catch (  MmsException e) {
    Timber.e(e,e.getMessage());
    return;
  }
  if (state == STATE_PERMANENT_FAILURE) {
    mHandler.post(new Runnable(){
      public void run(){
        try {
          Toast.makeText(mContext,getMessage(uri),Toast.LENGTH_LONG).show();
        }
 catch (        MmsException e) {
          Timber.e(e,e.getMessage());
        }
      }
    }
);
  }
 else   if (!mAutoDownload) {
    state|=DEFERRED_MASK;
  }
  ContentValues values=new ContentValues(1);
  values.put(Mms.STATUS,state);
  SqliteWrapper.update(mContext,mContext.getContentResolver(),uri,values,null,null);
}
