public static Uri persist(Context context,byte[] response,MmsConfig.Overridden mmsConfig,String locationUrl,int subId,String creator){
  notifyOfDownload(context);
  Timber.d("DownloadRequest.persistIfRequired");
  if (response == null || response.length < 1) {
    Timber.e("DownloadRequest.persistIfRequired: empty response");
    final ContentValues values=new ContentValues(1);
    values.put(Telephony.Mms.RETRIEVE_STATUS,PduHeaders.RETRIEVE_STATUS_ERROR_END);
    SqliteWrapper.update(context,context.getContentResolver(),Telephony.Mms.CONTENT_URI,values,LOCATION_SELECTION,new String[]{Integer.toString(PduHeaders.MESSAGE_TYPE_NOTIFICATION_IND),locationUrl});
    return null;
  }
  final long identity=Binder.clearCallingIdentity();
  try {
    final GenericPdu pdu=(new PduParser(response,mmsConfig.getSupportMmsContentDisposition())).parse();
    if (pdu == null || !(pdu instanceof RetrieveConf)) {
      Timber.e("DownloadRequest.persistIfRequired: invalid parsed PDU");
      setErrorType(context,locationUrl,Telephony.MmsSms.ERR_TYPE_MMS_PROTO_PERMANENT);
      return null;
    }
    final RetrieveConf retrieveConf=(RetrieveConf)pdu;
    final int status=retrieveConf.getRetrieveStatus();
    final PduPersister persister=PduPersister.getPduPersister(context);
    final Uri messageUri=persister.persist(pdu,Telephony.Mms.Inbox.CONTENT_URI,true,true,null);
    if (messageUri == null) {
      Timber.e("DownloadRequest.persistIfRequired: can not persist message");
      return null;
    }
    final ContentValues values=new ContentValues();
    values.put(Telephony.Mms.DATE,System.currentTimeMillis() / 1000L);
    values.put(Telephony.Mms.READ,0);
    values.put(Telephony.Mms.SEEN,0);
    if (!TextUtils.isEmpty(creator)) {
      values.put(Telephony.Mms.CREATOR,creator);
    }
    if (SubscriptionIdChecker.getInstance(context).canUseSubscriptionId()) {
      values.put(Telephony.Mms.SUBSCRIPTION_ID,subId);
    }
    if (SqliteWrapper.update(context,context.getContentResolver(),messageUri,values,null,null) != 1) {
      Timber.e("DownloadRequest.persistIfRequired: can not update message");
    }
    SqliteWrapper.delete(context,context.getContentResolver(),Telephony.Mms.CONTENT_URI,LOCATION_SELECTION,new String[]{Integer.toString(PduHeaders.MESSAGE_TYPE_NOTIFICATION_IND),locationUrl});
    return messageUri;
  }
 catch (  MmsException e) {
    Timber.e(e,"DownloadRequest.persistIfRequired: can not persist message");
  }
catch (  SQLiteException e) {
    Timber.e(e,"DownloadRequest.persistIfRequired: can not update message");
  }
catch (  RuntimeException e) {
    Timber.e(e,"DownloadRequest.persistIfRequired: can not parse response");
  }
 finally {
    Binder.restoreCallingIdentity(identity);
  }
  return null;
}
