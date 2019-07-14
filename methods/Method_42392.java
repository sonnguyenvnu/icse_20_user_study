/** 
 * Timestamp ?????yyyy-MM-dd timestampToSql(Timestamp ?????yyyy-MM-dd)
 * @param timestamp ??
 * @return createTimeStr yyyy-MM-dd ??
 * @Exception ????
 * @since V1.0
 */
public static String timestampToStringYMD(Timestamp timestamp){
  SimpleDateFormat sdf=new SimpleDateFormat(DateUtils.DATE_FORMAT_DATEONLY);
  String createTimeStr=sdf.format(timestamp);
  return createTimeStr;
}
