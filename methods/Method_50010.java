private void persistAddress(long msgId,int type,EncodedStringValue[] array){
  ContentValues values=new ContentValues(3);
  for (  EncodedStringValue addr : array) {
    values.clear();
    values.put(Addr.ADDRESS,toIsoString(addr.getTextString()));
    values.put(Addr.CHARSET,addr.getCharacterSet());
    values.put(Addr.TYPE,type);
    Uri uri=Uri.parse("content://mms/" + msgId + "/addr");
    SqliteWrapper.insert(mContext,mContentResolver,uri,values);
  }
}
