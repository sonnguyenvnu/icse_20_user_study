/** 
 * Update headers of a SendReq.
 * @param uri     The PDU which need to be updated.
 * @param sendReq New headers.
 * @throws MmsException Bad URI or updating failed.
 */
public void updateHeaders(Uri uri,SendReq sendReq){
synchronized (PDU_CACHE_INSTANCE) {
    if (PDU_CACHE_INSTANCE.isUpdating(uri)) {
      if (LOCAL_LOGV) {
        Timber.v("updateHeaders: " + uri + " blocked by isUpdating()");
      }
      try {
        PDU_CACHE_INSTANCE.wait();
      }
 catch (      InterruptedException e) {
        Timber.e(e,"updateHeaders: ");
      }
    }
  }
  PDU_CACHE_INSTANCE.purge(uri);
  ContentValues values=new ContentValues(10);
  byte[] contentType=sendReq.getContentType();
  if (contentType != null) {
    values.put(Mms.CONTENT_TYPE,toIsoString(contentType));
  }
  long date=sendReq.getDate();
  if (date != -1) {
    values.put(Mms.DATE,date);
  }
  int deliveryReport=sendReq.getDeliveryReport();
  if (deliveryReport != 0) {
    values.put(Mms.DELIVERY_REPORT,deliveryReport);
  }
  long expiry=sendReq.getExpiry();
  if (expiry != -1) {
    values.put(Mms.EXPIRY,expiry);
  }
  byte[] msgClass=sendReq.getMessageClass();
  if (msgClass != null) {
    values.put(Mms.MESSAGE_CLASS,toIsoString(msgClass));
  }
  int priority=sendReq.getPriority();
  if (priority != 0) {
    values.put(Mms.PRIORITY,priority);
  }
  int readReport=sendReq.getReadReport();
  if (readReport != 0) {
    values.put(Mms.READ_REPORT,readReport);
  }
  byte[] transId=sendReq.getTransactionId();
  if (transId != null) {
    values.put(Mms.TRANSACTION_ID,toIsoString(transId));
  }
  EncodedStringValue subject=sendReq.getSubject();
  if (subject != null) {
    values.put(Mms.SUBJECT,toIsoString(subject.getTextString()));
    values.put(Mms.SUBJECT_CHARSET,subject.getCharacterSet());
  }
 else {
    values.put(Mms.SUBJECT,"");
  }
  long messageSize=sendReq.getMessageSize();
  if (messageSize > 0) {
    values.put(Mms.MESSAGE_SIZE,messageSize);
  }
  PduHeaders headers=sendReq.getPduHeaders();
  HashSet<String> recipients=new HashSet<String>();
  for (  int addrType : ADDRESS_FIELDS) {
    EncodedStringValue[] array=null;
    if (addrType == PduHeaders.FROM) {
      EncodedStringValue v=headers.getEncodedStringValue(addrType);
      if (v != null) {
        array=new EncodedStringValue[1];
        array[0]=v;
      }
    }
 else {
      array=headers.getEncodedStringValues(addrType);
    }
    if (array != null) {
      long msgId=ContentUris.parseId(uri);
      updateAddress(msgId,addrType,array);
      if (addrType == PduHeaders.TO) {
        for (        EncodedStringValue v : array) {
          if (v != null) {
            recipients.add(v.getString());
          }
        }
      }
    }
  }
  if (!recipients.isEmpty()) {
    long threadId=Threads.getOrCreateThreadId(mContext,recipients);
    values.put(Mms.THREAD_ID,threadId);
  }
  SqliteWrapper.update(mContext,mContentResolver,uri,values,null,null);
}
