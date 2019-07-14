public ConcurrentRadixTree<VoidValue> getHiddenFilesConcurrentRadixTree(){
  ConcurrentRadixTree<VoidValue> paths=new ConcurrentRadixTree<>(new DefaultCharArrayNodeFactory());
  Cursor cursor=getReadableDatabase().query(getTableForOperation(Operation.HIDDEN),null,null,null,null,null,null);
  boolean hasNext=cursor.moveToFirst();
  while (hasNext) {
    paths.put(cursor.getString(cursor.getColumnIndex(COLUMN_PATH)),VoidValue.SINGLETON);
    hasNext=cursor.moveToNext();
  }
  cursor.close();
  return paths;
}
