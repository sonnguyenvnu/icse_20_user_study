/** 
 * ?????????java.sql.Timestamp?String
 * @param dt java.sql.Timestamp instance
 * @param sFmt Date ???DATE_FORMAT_DATEONLY/DATE_FORMAT_DATETIME/ DATE_FORMAT_SESSION
 * @return
 * @since 1.0
 * @history
 */
public static String toSqlTimestampString(Timestamp dt,String sFmt){
  String temp=null;
  String out=null;
  if (dt == null || sFmt == null) {
    return null;
  }
  temp=dt.toString();
  if (sFmt.equals(DateUtils.DATE_FORMAT_DATETIME) || sFmt.equals(DateUtils.DATE_FORMAT_DATEONLY)) {
    temp=temp.substring(0,sFmt.length());
    out=temp.replace('/','-');
  }
  return out;
}
