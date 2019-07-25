/** 
 * ?????
 * @param time
 * @param dateFormatter
 * @return
 */
public static long format(String time,DateTimeFormatter dateFormatter){
  if (YYYY_MM_DD_HH_MM_SS_0.equals(dateFormatter)) {
    return YYYY_MM_DD_HH_MM_SS_0.parseMillis(time);
  }
 else   if (YYYY_MM_DD_HH_MM_SS.equals(dateFormatter)) {
    return YYYY_MM_DD_HH_MM_SS.parseMillis(time);
  }
 else   if (YYYY_MM_DD_HH_MM.equals(dateFormatter)) {
    return YYYY_MM_DD_HH_MM.parseMillis(time);
  }
 else   if (YYYY_MM_DD.equals(dateFormatter)) {
    return YYYY_MM_DD.parseMillis(time);
  }
  return YYYY_MM_DD_HH_MM_SS.parseMillis(time);
}
