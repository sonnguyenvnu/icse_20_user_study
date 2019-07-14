public void run(){
  try {
    DownloadManager.init(mContext.getApplicationContext());
    DownloadManager.getInstance().markState(mUri,DownloadManager.STATE_DOWNLOADING);
    byte[] resp=getPdu(mContentLocation);
    RetrieveConf retrieveConf=(RetrieveConf)new PduParser(resp).parse();
    if (null == retrieveConf) {
      throw new MmsException("Invalid M-Retrieve.conf PDU.");
    }
    Uri msgUri=null;
    if (isDuplicateMessage(mContext,retrieveConf)) {
      mTransactionState.setState(TransactionState.FAILED);
      mTransactionState.setContentUri(mUri);
    }
 else {
      PduPersister persister=PduPersister.getPduPersister(mContext);
      msgUri=persister.persist(retrieveConf,Inbox.CONTENT_URI,true,true,null);
      ContentValues values=new ContentValues(2);
      values.put(Mms.DATE,System.currentTimeMillis() / 1000L);
      values.put(Mms.MESSAGE_SIZE,resp.length);
      SqliteWrapper.update(mContext,mContext.getContentResolver(),msgUri,values,null,null);
      mTransactionState.setState(TransactionState.SUCCESS);
      mTransactionState.setContentUri(msgUri);
      updateContentLocation(mContext,msgUri,mContentLocation,mLocked);
    }
    SqliteWrapper.delete(mContext,mContext.getContentResolver(),mUri,null,null);
    sendAcknowledgeInd(retrieveConf);
  }
 catch (  Throwable t) {
    Timber.e(t,"error");
    if ("HTTP error: Not Found".equals(t.getMessage())) {
      SqliteWrapper.delete(mContext,mContext.getContentResolver(),mUri,null,null);
    }
  }
 finally {
    if (mTransactionState.getState() != TransactionState.SUCCESS) {
      mTransactionState.setState(TransactionState.FAILED);
      mTransactionState.setContentUri(mUri);
      Timber.e("Retrieval failed.");
    }
    notifyObservers();
  }
}
