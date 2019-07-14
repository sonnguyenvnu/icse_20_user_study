/** 
 * ????????????
 * @param date ??1
 * @param otherDate ??2
 * @param withUnit ??????Calendar field??
 * @return ????0?, ??????0?? ??????0??
 */
public static int compareTime(Date date,Date otherDate,int withUnit){
  Calendar dateCal=Calendar.getInstance();
  dateCal.setTime(date);
  Calendar otherDateCal=Calendar.getInstance();
  otherDateCal.setTime(otherDate);
  dateCal.clear(Calendar.YEAR);
  dateCal.clear(Calendar.MONTH);
  dateCal.set(Calendar.DATE,1);
  otherDateCal.clear(Calendar.YEAR);
  otherDateCal.clear(Calendar.MONTH);
  otherDateCal.set(Calendar.DATE,1);
switch (withUnit) {
case Calendar.HOUR:
    dateCal.clear(Calendar.MINUTE);
  otherDateCal.clear(Calendar.MINUTE);
case Calendar.MINUTE:
dateCal.clear(Calendar.SECOND);
otherDateCal.clear(Calendar.SECOND);
case Calendar.SECOND:
dateCal.clear(Calendar.MILLISECOND);
otherDateCal.clear(Calendar.MILLISECOND);
case Calendar.MILLISECOND:
break;
default :
throw new IllegalArgumentException("withUnit ???? " + withUnit + " ?????");
}
return dateCal.compareTo(otherDateCal);
}
