/** 
 * ?????? ???
 * @return
 */
public List<String> getProvinceAllCity(String province){
  province=StringUtil.getTrimedString(province);
  if (province.length() <= 0) {
    return null;
  }
  List<String> list=new ArrayList<String>();
  Cursor c=db.rawQuery("SELECT distinct city from " + CITY_TABLE_NAME + " where province = ? ",new String[]{province});
  while (c.moveToNext()) {
    String city=c.getString(c.getColumnIndex("city"));
    list.add(city);
  }
  return list;
}
