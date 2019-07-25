public Map<String,String> search(String search){
  Map<String,String> map=new HashMap<String,String>();
  Cursor cursor=getReadableDatabase().query("overrides",new String[]{"name","value"},"name LIKE ?",new String[]{search},null,null,null,null);
  if (cursor != null) {
    while (cursor.moveToNext()) {
      map.put(cursor.getString(0),cursor.getString(1));
    }
    cursor.close();
  }
  cursor=getReadableDatabase().query("main",new String[]{"name","value"},"name LIKE ?",new String[]{search},null,null,null,null);
  if (cursor != null) {
    if (cursor.moveToNext()) {
      if (!map.containsKey(cursor.getString(0)))       map.put(cursor.getString(0),cursor.getString(1));
    }
    cursor.close();
  }
  return map;
}
