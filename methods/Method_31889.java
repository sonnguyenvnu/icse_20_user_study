private long getDateTimeMillis0(int year,int monthOfYear,int dayOfMonth,int millisOfDay){
  long dayInstant=getDateMidnightMillis(year,monthOfYear,dayOfMonth);
  if (dayInstant == Long.MIN_VALUE) {
    dayInstant=getDateMidnightMillis(year,monthOfYear,dayOfMonth + 1);
    millisOfDay=millisOfDay - 86400000;
  }
  long result=dayInstant + millisOfDay;
  if (result < 0 && dayInstant > 0) {
    return Long.MAX_VALUE;
  }
 else   if (result > 0 && dayInstant < 0) {
    return Long.MIN_VALUE;
  }
  return result;
}
