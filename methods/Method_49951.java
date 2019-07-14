public final boolean isLimitSurpassed(){
  long oneHourAgo=System.currentTimeMillis() - ONE_HOUR;
  Cursor c=SqliteWrapper.query(mContext,mContext.getContentResolver(),Rate.CONTENT_URI,new String[]{"COUNT(*) AS rate"},Rate.SENT_TIME + ">" + oneHourAgo,null,null);
  if (c != null) {
    try {
      if (c.moveToFirst()) {
        boolean limit=c.getInt(0) >= RATE_LIMIT;
        c.close();
        return limit;
      }
    }
  finally {
      c.close();
    }
  }
  return false;
}
