private ArrayList<String> getDbTableSchema(Cursor c){
  ArrayList<String> result=new ArrayList<>();
  for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    if (!isCancelled()) {
      result.add(c.getString(1));
    }
 else     break;
  }
  return result;
}
