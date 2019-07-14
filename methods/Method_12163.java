public static Date endOfDay(Date d){
  return DateUtils.addSeconds(DateUtils.addDays(DateUtils.truncate(d,Calendar.DATE),1),-1);
}
