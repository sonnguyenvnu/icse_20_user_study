private void updateAddress(long msgId,int type,EncodedStringValue[] array){
  SqliteWrapper.delete(mContext,mContentResolver,Uri.parse("content://mms/" + msgId + "/addr"),Addr.TYPE + "=" + type,null);
  persistAddress(msgId,type,array);
}
