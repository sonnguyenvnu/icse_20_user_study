private ArrayList<String> getDbTableNames(Cursor c){
  ArrayList<String> result=new ArrayList<>();
  for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    for (int i=0; i < c.getColumnCount(); i++) {
      result.add(c.getString(i));
    }
  }
  return result;
}
