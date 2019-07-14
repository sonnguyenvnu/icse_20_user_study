public static String stringForMessageListDate(long date){
  try {
    date*=1000;
    Calendar rightNow=Calendar.getInstance();
    int day=rightNow.get(Calendar.DAY_OF_YEAR);
    rightNow.setTimeInMillis(date);
    int dateDay=rightNow.get(Calendar.DAY_OF_YEAR);
    if (Math.abs(System.currentTimeMillis() - date) >= 31536000000L) {
      return getInstance().formatterYear.format(new Date(date));
    }
 else {
      int dayDiff=dateDay - day;
      if (dayDiff == 0 || dayDiff == -1 && System.currentTimeMillis() - date < 60 * 60 * 8 * 1000) {
        return getInstance().formatterDay.format(new Date(date));
      }
 else       if (dayDiff > -7 && dayDiff <= -1) {
        return getInstance().formatterWeek.format(new Date(date));
      }
 else {
        return getInstance().formatterDayMonth.format(new Date(date));
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return "LOC_ERR";
}
