public static long format(Date date){
  return YYYY_MM_DD_HH_MM_SS.parseMillis(format(date,YYYY_MM_DD_HH_MM_SS));
}
