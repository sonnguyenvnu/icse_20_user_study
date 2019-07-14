/** 
 * ?????Date??(YYYY/MM/DD hh:mm:ss)??String?java.sql.Timestamp
 * @param sDate Date string
 * @param sFmt Date format DATE_FORMAT_DATEONLY/DATE_FORMAT_DATETIME
 * @return
 * @since 1.0
 * @history
 */
public static Timestamp toSqlTimestamp(String sDate,String sFmt){
  String temp=null;
  if (sDate == null || sFmt == null) {
    return null;
  }
  if (sDate.length() != sFmt.length()) {
    return null;
  }
  if (sFmt.equals(DateUtils.DATE_FORMAT_DATETIME)) {
    temp=sDate.replace('/','-');
    temp=temp + ".000000000";
  }
 else   if (sFmt.equals(DateUtils.DATE_FORMAT_DATEONLY)) {
    temp=sDate.replace('/','-');
    temp=temp + " 00:00:00.000000000";
  }
 else {
    return null;
  }
  return Timestamp.valueOf(temp);
}
