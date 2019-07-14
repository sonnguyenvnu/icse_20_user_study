private static String[] getDisplayNameArray(int field,boolean isLong,Locale locale){
  DateFormatSymbols dfs=new DateFormatSymbols(locale);
switch (field) {
case Calendar.AM_PM:
    return dfs.getAmPmStrings();
case Calendar.DAY_OF_WEEK:
  return isLong ? dfs.getWeekdays() : dfs.getShortWeekdays();
case Calendar.ERA:
return dfs.getEras();
case Calendar.MONTH:
return isLong ? dfs.getMonths() : dfs.getShortMonths();
}
return null;
}
