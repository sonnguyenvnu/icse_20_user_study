public List<String> getAllProvince(){
  List<String> list=new ArrayList<String>();
  Cursor c=db.rawQuery("SELECT distinct province from " + CITY_TABLE_NAME,null);
  while (c.moveToNext()) {
    String province=c.getString(c.getColumnIndex("province"));
    list.add(province);
  }
  return list;
}
