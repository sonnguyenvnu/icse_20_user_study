public static ContentValues getDefaults(String path){
  ContentValues values=new ContentValues();
  values.put(ALBUM_PATH,path);
  values.put(ALBUM_PINNED,0);
  values.put(ALBUM_SORTING_MODE,SortingMode.DATE.getValue());
  values.put(ALBUM_SORTING_ORDER,SortingOrder.DESCENDING.getValue());
  values.put(ALBUM_ID,-1);
  return values;
}
