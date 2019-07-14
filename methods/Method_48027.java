public static Long truncate(TruncateField field,long timestamp){
  GregorianCalendar cal=DateUtils.getCalendar(timestamp);
switch (field) {
case MONTH:
    cal.set(DAY_OF_MONTH,1);
  return cal.getTimeInMillis();
case WEEK_NUMBER:
int firstWeekday=cal.getFirstDayOfWeek();
int weekday=cal.get(DAY_OF_WEEK);
int delta=weekday - firstWeekday;
if (delta < 0) delta+=7;
cal.add(Calendar.DAY_OF_YEAR,-delta);
return cal.getTimeInMillis();
case QUARTER:
int quarter=cal.get(Calendar.MONTH) / 3;
cal.set(DAY_OF_MONTH,1);
cal.set(Calendar.MONTH,quarter * 3);
return cal.getTimeInMillis();
case YEAR:
cal.set(Calendar.MONTH,Calendar.JANUARY);
cal.set(DAY_OF_MONTH,1);
return cal.getTimeInMillis();
default :
throw new IllegalArgumentException();
}
}
