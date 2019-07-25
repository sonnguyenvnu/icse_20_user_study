/** 
 * ?????????? "UTC 1970-01-01 00:00:00" ??????? ???????????????????????????????????? ???????????: <pre> yyyy-MM-dd HH:mm:ss yyyy-MM-dd HH:mm:ss.SSS yy-MM-dd HH:mm:ss; yy-MM-dd HH:mm:ss.SSS; yyyy-MM-dd; yy-MM-dd; HH:mm:ss; HH:mm:ss.SSS; </pre> ?????????? +8 ?? +8:00 ?? GMT+8:00 ??? ?? -9 ?? -9:00 ?? GMT-9:00 ??
 * @param ds ?????
 * @param tz ?????????????????
 * @return ??
 * @see #_P_TIME
 */
public static long ams(String ds,TimeZone tz){
  Matcher m=_P_TIME.matcher(ds);
  if (m.find()) {
    int yy=_int(m,2,1970);
    int MM=_int(m,4,1);
    int dd=_int(m,6,1);
    int HH=_int(m,9,0);
    int mm=_int(m,11,0);
    int ss=_int(m,14,0);
    int ms=_int(m,17,0);
    String str=String.format("%04d-%02d-%02d %02d:%02d:%02d.%03d",yy,MM,dd,HH,mm,ss,ms);
    SimpleDateFormat df=(SimpleDateFormat)DF_DATE_TIME_MS4.clone();
    if (null == tz && !Strings.isBlank(m.group(18))) {
      tz=TimeZone.getTimeZone(String.format("GMT%s%s:00",m.group(19),m.group(20)));
    }
    if (null != tz)     df.setTimeZone(tz);
    try {
      return df.parse(str).getTime();
    }
 catch (    ParseException e) {
      throw Lang.wrapThrow(e);
    }
  }
 else   if (_P_TIME_LONG.matcher(ds).find()) {
    if (ds.endsWith("L"))     ds.substring(0,ds.length() - 1);
    return Long.parseLong(ds);
  }
  throw Lang.makeThrow("Unexpect date format '%s'",ds);
}
