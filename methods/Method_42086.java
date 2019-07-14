@Override public void onCreate(SQLiteDatabase db){
  db.execSQL("CREATE TABLE " + TABLE_ALBUMS + "(" + ALBUM_PATH + " TEXT," + ALBUM_ID + " INTEGER," + ALBUM_PINNED + " INTEGER," + ALBUM_COVER_PATH + " TEXT, " + ALBUM_STATUS + " INTEGER, " + ALBUM_SORTING_MODE + " INTEGER, " + ALBUM_SORTING_ORDER + " INTEGER)");
  db.execSQL(String.format("CREATE UNIQUE INDEX idx_path ON %s (%s)",TABLE_ALBUMS,ALBUM_PATH));
}
