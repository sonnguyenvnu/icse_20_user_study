public Feed query(long id){
  Feed feed=null;
  Cursor cursor=query(null,FeedsDBInfo.CATEGORY + "=?" + " AND " + FeedsDBInfo.ID + "= ?",new String[]{String.valueOf(mCategory.ordinal()),String.valueOf(id)},null);
  if (cursor.moveToFirst()) {
    feed=Feed.fromCursor(cursor);
  }
  cursor.close();
  return feed;
}
