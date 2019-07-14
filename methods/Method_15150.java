/** 
 * ????? ???
 * @return
 */
public List<String> getAllCountry(String province,String city){
  province=StringUtil.getTrimedString(province);
  if (province.length() <= 0) {
    return null;
  }
  city=StringUtil.getTrimedString(city);
  if (city.length() <= 0) {
    return null;
  }
  List<String> list=new ArrayList<String>();
  Cursor c=db.rawQuery("SELECT country from " + CITY_TABLE_NAME + " where province = ? and city = ?",new String[]{province,city});
  while (c.moveToNext()) {
    String country=c.getString(c.getColumnIndex("country"));
    list.add(country);
  }
  return list;
}
