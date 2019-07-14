public static String formatWeekdayList(Context context,boolean weekday[]){
  String shortDayNames[]=org.isoron.uhabits.core.utils.DateUtils.getShortDayNames();
  String longDayNames[]=org.isoron.uhabits.core.utils.DateUtils.getLongDayNames();
  StringBuilder buffer=new StringBuilder();
  int count=0;
  int first=0;
  boolean isFirst=true;
  for (int i=0; i < 7; i++) {
    if (weekday[i]) {
      if (isFirst)       first=i;
 else       buffer.append(", ");
      buffer.append(shortDayNames[i]);
      isFirst=false;
      count++;
    }
  }
  if (count == 1)   return longDayNames[first];
  if (count == 2 && weekday[0] && weekday[1])   return context.getString(R.string.weekends);
  if (count == 5 && !weekday[0] && !weekday[1])   return context.getString(R.string.any_weekday);
  if (count == 7)   return context.getString(R.string.any_day);
  return buffer.toString();
}
