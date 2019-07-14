public int getState(Uri uri){
  Cursor cursor=SqliteWrapper.query(mContext,mContext.getContentResolver(),uri,new String[]{Mms.STATUS},null,null,null);
  if (cursor != null) {
    try {
      if (cursor.moveToFirst()) {
        int state=cursor.getInt(0) & ~DEFERRED_MASK;
        cursor.close();
        return state;
      }
    }
  finally {
      cursor.close();
    }
  }
  return STATE_UNSTARTED;
}
